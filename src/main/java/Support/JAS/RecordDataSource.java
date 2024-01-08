package Support.JAS;

import jas.hist.DataSource;
import jas.hist.HasDataSource;
import jas.hist.HasStyle;
import jas.hist.HistogramUpdate;
import jas.hist.JASHist;
import jas.hist.JASHist1DHistogramStyle;
import jas.hist.JASHistStyle;
import jas.hist.Rebinnable1DHistogramData;
import jas.hist.Statistics;
import jas.hist.util.ObserverAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;

import javax.swing.JOptionPane;

public class RecordDataSource extends ObserverAdapter implements HasDataSource, Rebinnable1DHistogramData, HasStyle
{


	private static final HistogramUpdate hdr = new HistogramUpdate(1, true);
	private static final int SECONDS = 100;
	private static MemoryThread t = null; //new MemoryThread("");
	public static JASHist MyViwer;
  public String queryString = "";
	/**
	 * @param plot
	 */
	public RecordDataSource(JASHist plot , Connection m_con , String m_queryString, String m_title) 
	{	
    super(t);    
    t = new MemoryThread(m_con, m_queryString , m_title); 
    this.setObservable(t); // --added by Shawky Foda after a very long investigation
		MyViwer = plot;

  }
  public RecordDataSource()
  {
    super(t);
  }

	private static class MemoryThread extends Observable implements Runnable
	{
		private Thread thread;
		private int index, Current_Value = 0, Previous_Value = 0, Sleep_Rate = 5, Current_Status, Avg_Index = 0;
		private double bins[];
		private double Rate = 0,Avg = 0;
		private Connection conn = null;
		private String Process_Status = "";
		public String queryString = "";
    public String title = "";
    public void run()
		{
			try
			{
				do
				{
					synchronized(this)
					{
						Current_Value = getLastValue();
						bins[index++] = Current_Value;
						if(index >= 100)
							index = 0;
					}
					
					if(Previous_Value != 0)
						Avg += (((double)Current_Value - (double)Previous_Value) / (double)Sleep_Rate) * 60;
					
					
					if(Avg_Index == 2)
					{
						Rate = Avg/3;
						Avg_Index = 0;
						Avg = 0;
					}
					else
					{
						Avg_Index++;
					}
					Previous_Value = Current_Value;
          try{
					MyViwer.setTitle(this.title + "- current Value= " +Current_Value+"  Rate: "+Rate+"/min");
          }
          catch(Exception e){}
					setChanged();
					notifyObservers(RecordDataSource.hdr);
					Thread.sleep((Sleep_Rate * 1000L));
				
				} while(true);
			}
			catch(InterruptedException interruptedexception)
			{
				try
				{
					conn.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
				return;
			}
		}

		synchronized double[] getSnapshot()
		{
			double ad[] = new double[100];
			int i = 0;
			for(int j = index; j < 100; j++)
				ad[i++] = bins[j];

			for(int k = 0; k < index; k++)
				ad[i++] = bins[k];

			return ad;
		}
		
		MemoryThread(Connection m_con , String m_queryString , String m_title)
		{
      
			this.queryString = m_queryString;
      this.title = m_title;
      this.conn = m_con;
      index = 0;
			bins = new double[100];
			for(int i = 0; i < 100; i++)
				bins[i] = (0.0D / 0.0D);

			thread = new Thread(this);
			thread.setDaemon(true);
			thread.start();
      
		}
		
		public int getLastValue()
		{
			int lastValue = 0;
			try
			{
			
			Statement stmt = conn.createStatement();
                
			ResultSet MyData = stmt.executeQuery(this.queryString);
			while(MyData.next())
			{
				lastValue = Integer.parseInt(MyData.getString(1));
			}
			MyData.close();
			stmt.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "unable to execute Query:\n " + this.queryString +" Due to:\n " + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
			return lastValue;
		}
		
		public String getProcessStatus()
		{
			String Status = "";
			try
			{
			
				Statement stmt = conn.createStatement();
				String sql = " select aa.operation, to_char(to_date(aa.CreateDate||'.'||aa.CreateTime,'j.sssss')+ 3/24,'mm/dd/yyyy hh24:mi:ss') KSA_TIME,p.Value as FileName ";
					   sql+= " from AuditLog AA, Property P,( ";
					   sql+= " select max(Createtime) as MaxTime ";
					   sql+= " from AuditLog a ";
					   sql+= " where a.application ='BILL_UPLOAD' ";
					   sql+= " and a.SERVERSITE = '001' ";
					   sql+= " and a.createdate >= 2453885 )";
					   sql+= " where AA.CreateDate = 2453885 "; 
				       sql+= "  and AA.CreateTime = MaxTime "; 
				       sql+= "  and p.idx = 9 ";		  
				       sql+= " and aa.id = p.id(+) ";	  
					  
				ResultSet MyData = stmt.executeQuery(sql);
				while(MyData.next())
				{
					Status = " "+MyData.getString("operation");
				}
				MyData.close();
				stmt.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}
				return Status;
		}
				

	}



	public DataSource getDataSource(String s)
	{
		return this;
	}

	public double[][] rebin(int i, double d, double d1, boolean flag, boolean flag1)
	{
		double ad[] = t.getSnapshot();
		double ad1[][] = {
			ad
		};
		return ad1;
	}

	public double getMin()
	{
		return -100D;
	}

	public double getMax()
	{
		return 0.0D;
	}

	public int getBins()
	{
		return 100;
	}

	public boolean isRebinnable()
	{
		return false;
	}

	public int getAxisType()
	{
		return 4;
	}

	public String[] getAxisLabels()
	{
		return null;
	}

	public Statistics getStatistics()
	{
		return null;
	}

	public String getTitle()
	{
		return "Records Processed";
	}

	public JASHistStyle getStyle()
	{
		JASHist1DHistogramStyle jashist1dhistogramstyle = new JASHist1DHistogramStyle();
		jashist1dhistogramstyle.setShowErrorBars(false);
		jashist1dhistogramstyle.setShowDataPoints(true);
		jashist1dhistogramstyle.setShowLinesBetweenPoints(true);
		jashist1dhistogramstyle.setShowHistogramBars(false);
		return jashist1dhistogramstyle;
	}

}


function populateStateSales(chart, state) {
    var items;

    if (state == 'AK') {
        items = [
  {
      "County": "Aleutians East",
      "Sales": 57.27
  },
  {
      "County": "Aleutians West",
      "Sales": 102.07
  },
  {
      "County": "Anchorage",
      "Sales": 5574.12
  },
  {
      "County": "Bethel",
      "Sales": 328.91
  },
  {
      "County": "Bristol Bay",
      "Sales": 17.78
  },
  {
      "County": "Denali",
      "Sales": 34.58
  },
  {
      "County": "Dillingham",
      "Sales": 92.79
  },
  {
      "County": "Fairbanks North Star",
      "Sales": 1860.25
  },
  {
      "County": "Haines",
      "Sales": 48.01
  },
  {
      "County": "Hoonah-Angoon",
      "Sales": 39.73
  },
  {
      "County": "Juneau",
      "Sales": 604.92
  },
  {
      "County": "Kenai Peninsula",
      "Sales": 1058.46
  },
  {
      "County": "Ketchikan Gateway",
      "Sales": 254.29
  },
  {
      "County": "Kodiak Island",
      "Sales": 261.81
  },
  {
      "County": "Lake and Peninsula",
      "Sales": 30.52
  },
  {
      "County": "Matanuska-Susitna",
      "Sales": 1763.12
  },
  {
      "County": "Nome",
      "Sales": 183.22
  },
  {
      "County": "North Slope",
      "Sales": 179.40
  },
  {
      "County": "Northwest Arctic",
      "Sales": 142.34
  },
  {
      "County": "Petersburg",
      "Sales": 69.90
  },
  {
      "County": "Prince of Wales-Hyder",
      "Sales": 107.17
  },
  {
      "County": "Sitka",
      "Sales": 167.07
  },
  {
      "County": "Skagway",
      "Sales": 18.43
  },
  {
      "County": "Southeast Fairbanks",
      "Sales": 129.37
  },
  {
      "County": "Valdez-Cordova",
      "Sales": 180.83
  },
  {
      "County": "Wade Hampton",
      "Sales": 147.75
  },
  {
      "County": "Wrangell",
      "Sales": 44.45
  },
  {
      "County": "Yakutat",
      "Sales": 11.89
  },
  {
      "County": "Yukon-Koyukuk",
      "Sales": 105.48
  }
        ];
    }

    if (state == 'AL') {
        items = [
    {
        "County": "Autauga",
        "Sales": 713.58
    },
    {
        "County": "Baldwin",
        "Sales": 2525.68
    },
    {
        "County": "Barbour",
        "Sales": 349.73
    },
    {
        "County": "Bibb",
        "Sales": 290.77
    },
    {
        "County": "Blount",
        "Sales": 747.50
    },
    {
        "County": "Bullock",
        "Sales": 137.42
    },
    {
        "County": "Butler",
        "Sales": 261.75
    },
    {
        "County": "Calhoun",
        "Sales": 1507.81
    },
    {
        "County": "Chambers",
        "Sales": 441.25
    },
    {
        "County": "Cherokee",
        "Sales": 338.45
    },
    {
        "County": "Chilton",
        "Sales": 567.69
    },
    {
        "County": "Choctaw",
        "Sales": 173.42
    },
    {
        "County": "Clarke",
        "Sales": 325.58
    },
    {
        "County": "Clay",
        "Sales": 174.19
    },
    {
        "County": "Cleburne",
        "Sales": 193.67
    },
    {
        "County": "Coffee",
        "Sales": 657.94
    },
    {
        "County": "Colbert",
        "Sales": 704.20
    },
    {
        "County": "Conecuh",
        "Sales": 166.45
    },
    {
        "County": "Coosa",
        "Sales": 140.76
    },
    {
        "County": "Covington",
        "Sales": 489.35
    },
    {
        "County": "Crenshaw",
        "Sales": 180.65
    },
    {
        "County": "Cullman",
        "Sales": 1043.79
    },
    {
        "County": "Dale",
        "Sales": 644.32
    },
    {
        "County": "Dallas",
        "Sales": 542.44
    },
    {
        "County": "DeKalb",
        "Sales": 917.24
    },
    {
        "County": "Elmore",
        "Sales": 1044.97
    },
    {
        "County": "Escambia",
        "Sales": 490.61
    },
    {
        "County": "Etowah",
        "Sales": 1342.42
    },
    {
        "County": "Fayette",
        "Sales": 218.40
    },
    {
        "County": "Franklin",
        "Sales": 407.28
    },
    {
        "County": "Geneva",
        "Sales": 345.22
    },
    {
        "County": "Greene",
        "Sales": 112.94
    },
    {
        "County": "Hale",
        "Sales": 198.99
    },
    {
        "County": "Henry",
        "Sales": 223.40
    },
    {
        "County": "Houston",
        "Sales": 1339.02
    },
    {
        "County": "Jackson",
        "Sales": 683.94
    },
    {
        "County": "Jefferson",
        "Sales": 8518.12
    },
    {
        "County": "Lamar",
        "Sales": 183.88
    },
    {
        "County": "Lauderdale",
        "Sales": 1198.61
    },
    {
        "County": "Lawrence",
        "Sales": 433.82
    },
    {
        "County": "Lee",
        "Sales": 1949.52
    },
    {
        "County": "Limestone",
        "Sales": 1147.56
    },
    {
        "County": "Lowndes",
        "Sales": 138.24
    },
    {
        "County": "Macon",
        "Sales": 254.30
    },
    {
        "County": "Madison",
        "Sales": 4480.61
    },
    {
        "County": "Marengo",
        "Sales": 260.33
    },
    {
        "County": "Marion",
        "Sales": 391.81
    },
    {
        "County": "Marshall",
        "Sales": 1223.96
    },
    {
        "County": "Mobile",
        "Sales": 5348.43
    },
    {
        "County": "Monroe",
        "Sales": 287.21
    },
    {
        "County": "Montgomery",
        "Sales": 2927.63
    },
    {
        "County": "Morgan",
        "Sales": 1547.22
    },
    {
        "County": "Perry",
        "Sales": 129.42
    },
    {
        "County": "Pickens",
        "Sales": 250.59
    },
    {
        "County": "Pike",
        "Sales": 430.62
    },
    {
        "County": "Randolph",
        "Sales": 293.55
    },
    {
        "County": "Russell",
        "Sales": 769.63
    },
    {
        "County": "St. Clair",
        "Sales": 1114.79
    },
    {
        "County": "Shelby",
        "Sales": 2637.28
    },
    {
        "County": "Sumter",
        "Sales": 172.58
    },
    {
        "County": "Talladega",
        "Sales": 1047.47
    },
    {
        "County": "Tallapoosa",
        "Sales": 532.20
    },
    {
        "County": "Tuscaloosa",
        "Sales": 2593.89
    },
    {
        "County": "Walker",
        "Sales": 852.46
    },
    {
        "County": "Washington",
        "Sales": 217.99
    },
    {
        "County": "Wilcox",
        "Sales": 146.05
    },
    {
        "County": "Winston",
        "Sales": 311.88
    }
        ];
    }

    if (state == 'AR') {
        items = [
  {
      "County": "Arkansas",
      "Sales": 176.02
  },
  {
      "County": "Ashley",
      "Sales": 199.51
  },
  {
      "County": "Baxter",
      "Sales": 383.93
  },
  {
      "County": "Benton",
      "Sales": 2224.42
  },
  {
      "County": "Boone",
      "Sales": 350.55
  },
  {
      "County": "Bradley",
      "Sales": 105.45
  },
  {
      "County": "Calhoun",
      "Sales": 49.13
  },
  {
      "County": "Carroll",
      "Sales": 260.67
  },
  {
      "County": "Chicot",
      "Sales": 106.25
  },
  {
      "County": "Clark",
      "Sales": 213.19
  },
  {
      "County": "Clay",
      "Sales": 144.38
  },
  {
      "County": "Cleburne",
      "Sales": 240.78
  },
  {
      "County": "Cleveland",
      "Sales": 80.55
  },
  {
      "County": "Columbia",
      "Sales": 226.51
  },
  {
      "County": "Conway",
      "Sales": 199.15
  },
  {
      "County": "Craighead",
      "Sales": 951.35
  },
  {
      "County": "Crawford",
      "Sales": 577.81
  },
  {
      "County": "Crittenden",
      "Sales": 466.32
  },
  {
      "County": "Cross",
      "Sales": 164.49
  },
  {
      "County": "Dallas",
      "Sales": 74.36
  },
  {
      "County": "Desha",
      "Sales": 117.22
  },
  {
      "County": "Drew",
      "Sales": 176.09
  },
  {
      "County": "Faulkner",
      "Sales": 1120.94
  },
  {
      "County": "Franklin",
      "Sales": 169.05
  },
  {
      "County": "Fulton",
      "Sales": 115.34
  },
  {
      "County": "Garland",
      "Sales": 910.90
  },
  {
      "County": "Grant",
      "Sales": 168.91
  },
  {
      "County": "Greene",
      "Sales": 403.99
  },
  {
      "County": "Hempstead",
      "Sales": 210.67
  },
  {
      "County": "Hot Spring",
      "Sales": 314.03
  },
  {
      "County": "Howard",
      "Sales": 127.31
  },
  {
      "County": "Independence",
      "Sales": 346.81
  },
  {
      "County": "Izard",
      "Sales": 125.31
  },
  {
      "County": "Jackson",
      "Sales": 165.12
  },
  {
      "County": "Jefferson",
      "Sales": 686.09
  },
  {
      "County": "Johnson",
      "Sales": 242.28
  },
  {
      "County": "Lafayette",
      "Sales": 67.98
  },
  {
      "County": "Lawrence",
      "Sales": 159.46
  },
  {
      "County": "Lee",
      "Sales": 93.88
  },
  {
      "County": "Lincoln",
      "Sales": 131.53
  },
  {
      "County": "Little River",
      "Sales": 119.33
  },
  {
      "County": "Logan",
      "Sales": 207.00
  },
  {
      "County": "Lonoke",
      "Sales": 663.24
  },
  {
      "County": "Madison",
      "Sales": 147.18
  },
  {
      "County": "Marion",
      "Sales": 154.01
  },
  {
      "County": "Miller",
      "Sales": 406.85
  },
  {
      "County": "Mississippi",
      "Sales": 419.63
  },
  {
      "County": "Monroe",
      "Sales": 72.01
  },
  {
      "County": "Montgomery",
      "Sales": 86.48
  },
  {
      "County": "Nevada",
      "Sales": 82.48
  },
  {
      "County": "Newton",
      "Sales": 75.59
  },
  {
      "County": "Ouachita",
      "Sales": 234.37
  },
  {
      "County": "Perry",
      "Sales": 96.97
  },
  {
      "County": "Phillips",
      "Sales": 191.22
  },
  {
      "County": "Pike",
      "Sales": 104.77
  },
  {
      "County": "Poinsett",
      "Sales": 226.33
  },
  {
      "County": "Polk",
      "Sales": 191.29
  },
  {
      "County": "Pope",
      "Sales": 586.31
  },
  {
      "County": "Prairie",
      "Sales": 78.50
  },
  {
      "County": "Pulaski",
      "Sales": 3667.89
  },
  {
      "County": "Randolph",
      "Sales": 165.84
  },
  {
      "County": "St. Francis",
      "Sales": 255.53
  },
  {
      "County": "Saline",
      "Sales": 1072.42
  },
  {
      "County": "Scott",
      "Sales": 102.65
  },
  {
      "County": "Searcy",
      "Sales": 75.21
  },
  {
      "County": "Sebastian",
      "Sales": 1193.70
  },
  {
      "County": "Sevier",
      "Sales": 162.79
  },
  {
      "County": "Sharp",
      "Sales": 159.82
  },
  {
      "County": "Stone",
      "Sales": 117.93
  },
  {
      "County": "Union",
      "Sales": 381.47
  },
  {
      "County": "Van Buren",
      "Sales": 158.72
  },
  {
      "County": "Washington",
      "Sales": 2028.62
  },
  {
      "County": "White",
      "Sales": 735.70
  },
  {
      "County": "Woodruff",
      "Sales": 66.29
  },
  {
      "County": "Yell",
      "Sales": 205.22
  }
        ];
    }

    if (state == 'AZ') {
        items = [
  {
      "County": "Apache",
      "Sales": 375.19
  },
  {
      "County": "Cochise",
      "Sales": 675.29
  },
  {
      "County": "Coconino",
      "Sales": 712.15
  },
  {
      "County": "Gila",
      "Sales": 276.71
  },
  {
      "County": "Graham",
      "Sales": 195.50
  },
  {
      "County": "Greenlee",
      "Sales": 47.20
  },
  {
      "County": "La Paz",
      "Sales": 106.00
  },
  {
      "County": "Maricopa",
      "Sales": 20911.91
  },
  {
      "County": "Mohave",
      "Sales": 1058.94
  },
  {
      "County": "Navajo",
      "Sales": 559.76
  },
  {
      "County": "Pima",
      "Sales": 5197.73
  },
  {
      "County": "Pinal",
      "Sales": 2030.73
  },
  {
      "County": "Santa Cruz",
      "Sales": 243.93
  },
  {
      "County": "Yavapai",
      "Sales": 1122.07
  },
  {
      "County": "Yuma",
      "Sales": 1049.40
  }
        ];
    }

    if (state == 'CA') {
        items = [
  {
      "County": "Alameda",
      "Sales": 12166.00
  },
  {
      "County": "Alpine",
      "Sales": 8.93
  },
  {
      "County": "Amador",
      "Sales": 281.39
  },
  {
      "County": "Butte",
      "Sales": 1711.29
  },
  {
      "County": "Calaveras",
      "Sales": 343.01
  },
  {
      "County": "Colusa",
      "Sales": 164.57
  },
  {
      "County": "Contra Costa",
      "Sales": 8431.29
  },
  {
      "County": "Del Norte",
      "Sales": 214.77
  },
  {
      "County": "El Dorado",
      "Sales": 1400.36
  },
  {
      "County": "Fresno",
      "Sales": 7360.76
  },
  {
      "County": "Glenn",
      "Sales": 215.29
  },
  {
      "County": "Humboldt",
      "Sales": 1036.32
  },
  {
      "County": "Imperial",
      "Sales": 1360.65
  },
  {
      "County": "Inyo",
      "Sales": 142.30
  },
  {
      "County": "Kern",
      "Sales": 6658.43
  },
  {
      "County": "Kings",
      "Sales": 1163.21
  },
  {
      "County": "Lake",
      "Sales": 492.07
  },
  {
      "County": "Lassen",
      "Sales": 247.83
  },
  {
      "County": "Los Angeles",
      "Sales": 77185.58
  },
  {
      "County": "Madera",
      "Sales": 1174.22
  },
  {
      "County": "Marin",
      "Sales": 1990.81
  },
  {
      "County": "Mariposa",
      "Sales": 136.81
  },
  {
      "County": "Mendocino",
      "Sales": 671.85
  },
  {
      "County": "Merced",
      "Sales": 2028.28
  },
  {
      "County": "Modoc",
      "Sales": 70.48
  },
  {
      "County": "Mono",
      "Sales": 108.45
  },
  {
      "County": "Monterey",
      "Sales": 3304.28
  },
  {
      "County": "Napa",
      "Sales": 1081.27
  },
  {
      "County": "Nevada",
      "Sales": 756.67
  },
  {
      "County": "Orange",
      "Sales": 23997.43
  },
  {
      "County": "Placer",
      "Sales": 2830.26
  },
  {
      "County": "Plumas",
      "Sales": 145.32
  },
  {
      "County": "Riverside",
      "Sales": 17664.70
  },
  {
      "County": "Sacramento",
      "Sales": 11266.31
  },
  {
      "County": "San Benito",
      "Sales": 443.83
  },
  {
      "County": "San Bernardino",
      "Sales": 16091.75
  },
  {
      "County": "San Diego",
      "Sales": 24744.00
  },
  {
      "County": "San Francisco",
      "Sales": 6452.83
  },
  {
      "County": "San Joaquin",
      "Sales": 5427.53
  },
  {
      "County": "San Luis Obispo",
      "Sales": 2130.11
  },
  {
      "County": "San Mateo",
      "Sales": 5758.81
  },
  {
      "County": "Santa Barbara",
      "Sales": 3357.22
  },
  {
      "County": "Santa Clara",
      "Sales": 14347.78
  },
  {
      "County": "Santa Cruz",
      "Sales": 2075.98
  },
  {
      "County": "Shasta",
      "Sales": 1379.11
  },
  {
      "County": "Sierra",
      "Sales": 23.48
  },
  {
      "County": "Siskiyou",
      "Sales": 337.49
  },
  {
      "County": "Solano",
      "Sales": 3273.16
  },
  {
      "County": "Sonoma",
      "Sales": 3814.37
  },
  {
      "County": "Stanislaus",
      "Sales": 4049.12
  },
  {
      "County": "Sutter",
      "Sales": 734.71
  },
  {
      "County": "Tehama",
      "Sales": 485.88
  },
  {
      "County": "Trinity",
      "Sales": 103.62
  },
  {
      "County": "Tulare",
      "Sales": 3499.36
  },
  {
      "County": "Tuolumne",
      "Sales": 415.12
  },
  {
      "County": "Ventura",
      "Sales": 6469.61
  },
  {
      "County": "Yolo",
      "Sales": 1576.47
  },
  {
      "County": "Yuba",
      "Sales": 565.11
  }
        ];
    }

    if (state == 'CO') {
        items = [
  {
      "County": "Adams",
      "Sales": 3992.40
  },
  {
      "County": "Alamosa",
      "Sales": 138.30
  },
  {
      "County": "Arapahoe",
      "Sales": 5165.60
  },
  {
      "County": "Archuleta",
      "Sales": 103.76
  },
  {
      "County": "Baca",
      "Sales": 31.33
  },
  {
      "County": "Bent",
      "Sales": 48.40
  },
  {
      "County": "Boulder",
      "Sales": 2638.22
  },
  {
      "County": "Broomfield",
      "Sales": 506.04
  },
  {
      "County": "Chaffee",
      "Sales": 157.50
  },
  {
      "County": "Cheyenne",
      "Sales": 16.08
  },
  {
      "County": "Clear Creek",
      "Sales": 76.85
  },
  {
      "County": "Conejos",
      "Sales": 70.43
  },
  {
      "County": "Costilla",
      "Sales": 29.93
  },
  {
      "County": "Crowley",
      "Sales": 45.29
  },
  {
      "County": "Custer",
      "Sales": 36.46
  },
  {
      "County": "Delta",
      "Sales": 259.38
  },
  {
      "County": "Denver",
      "Sales": 5526.60
  },
  {
      "County": "Dolores",
      "Sales": 17.26
  },
  {
      "County": "Douglas",
      "Sales": 2603.46
  },
  {
      "County": "Eagle",
      "Sales": 446.39
  },
  {
      "County": "Elbert",
      "Sales": 201.95
  },
  {
      "County": "El Paso",
      "Sales": 5573.81
  },
  {
      "County": "Fremont",
      "Sales": 395.25
  },
  {
      "County": "Garfield",
      "Sales": 487.59
  },
  {
      "County": "Gilpin",
      "Sales": 47.66
  },
  {
      "County": "Grand",
      "Sales": 121.59
  },
  {
      "County": "Gunnison",
      "Sales": 131.95
  },
  {
      "County": "Hinsdale",
      "Sales": 6.92
  },
  {
      "County": "Huerfano",
      "Sales": 55.47
  },
  {
      "County": "Jackson",
      "Sales": 11.61
  },
  {
      "County": "Jefferson",
      "Sales": 4695.29
  },
  {
      "County": "Kiowa",
      "Sales": 12.11
  },
  {
      "County": "Kit Carson",
      "Sales": 68.39
  },
  {
      "County": "Lake",
      "Sales": 62.17
  },
  {
      "County": "La Plata",
      "Sales": 453.40
  },
  {
      "County": "Larimer",
      "Sales": 2688.76
  },
  {
      "County": "Las Animas",
      "Sales": 122.92
  },
  {
      "County": "Lincoln",
      "Sales": 46.20
  },
  {
      "County": "Logan",
      "Sales": 191.03
  },
  {
      "County": "Mesa",
      "Sales": 1255.55
  },
  {
      "County": "Mineral",
      "Sales": 6.14
  },
  {
      "County": "Moffat",
      "Sales": 111.49
  },
  {
      "County": "Montezuma",
      "Sales": 218.19
  },
  {
      "County": "Montrose",
      "Sales": 346.43
  },
  {
      "County": "Morgan",
      "Sales": 241.69
  },
  {
      "County": "Otero",
      "Sales": 159.15
  },
  {
      "County": "Ouray",
      "Sales": 38.78
  },
  {
      "County": "Park",
      "Sales": 137.17
  },
  {
      "County": "Phillips",
      "Sales": 37.07
  },
  {
      "County": "Pitkin",
      "Sales": 147.88
  },
  {
      "County": "Prowers",
      "Sales": 104.58
  },
  {
      "County": "Pueblo",
      "Sales": 1373.80
  },
  {
      "County": "Rio Blanco",
      "Sales": 57.92
  },
  {
      "County": "Rio Grande",
      "Sales": 100.43
  },
  {
      "County": "Routt",
      "Sales": 200.07
  },
  {
      "County": "Saguache",
      "Sales": 52.82
  },
  {
      "County": "San Juan",
      "Sales": 5.89
  },
  {
      "County": "San Miguel",
      "Sales": 65.33
  },
  {
      "County": "Sedgwick",
      "Sales": 20.08
  },
  {
      "County": "Summit",
      "Sales": 243.78
  },
  {
      "County": "Teller",
      "Sales": 198.05
  },
  {
      "County": "Washington",
      "Sales": 40.87
  },
  {
      "County": "Weld",
      "Sales": 2295.62
  },
  {
      "County": "Yuma",
      "Sales": 86.38
  }
        ];
    }

    if (state == 'CT') {
        items = [
  {
      "County": "Fairfield",
      "Sales": 6580.11
  },
  {
      "County": "Hartford",
      "Sales": 6288.65
  },
  {
      "County": "Litchfield",
      "Sales": 1308.62
  },
  {
      "County": "Middlesex",
      "Sales": 1159.07
  },
  {
      "County": "New Haven",
      "Sales": 6036.73
  },
  {
      "County": "New London",
      "Sales": 1919.28
  },
  {
      "County": "Tolland",
      "Sales": 1059.76
  },
  {
      "County": "Windham",
      "Sales": 823.33
  }
        ];
    }

    if (state == 'DC') {
        items = [
  {
      "County": "District of Columbia",
      "Sales": 25175.55
  }
        ];
    }

    if (state == 'DE') {
        items = [
  {
      "County": "Kent",
      "Sales": 1738.40
  },
  {
      "County": "New Castle",
      "Sales": 5640.40
  },
  {
      "County": "Sussex",
      "Sales": 2120.46
  }
        ];
    }

    if (state == 'FL') {
        items = [
  {
      "County": "Alachua",
      "Sales": 2402.86
  },
  {
      "County": "Baker",
      "Sales": 256.10
  },
  {
      "County": "Bay",
      "Sales": 1658.98
  },
  {
      "County": "Bradford",
      "Sales": 254.55
  },
  {
      "County": "Brevard",
      "Sales": 5222.12
  },
  {
      "County": "Broward",
      "Sales": 17433.31
  },
  {
      "County": "Calhoun",
      "Sales": 139.19
  },
  {
      "County": "Charlotte",
      "Sales": 1561.79
  },
  {
      "County": "Citrus",
      "Sales": 1320.37
  },
  {
      "County": "Clay",
      "Sales": 1861.98
  },
  {
      "County": "Collier",
      "Sales": 3220.00
  },
  {
      "County": "Columbia",
      "Sales": 640.35
  },
  {
      "County": "DeSoto",
      "Sales": 327.24
  },
  {
      "County": "Dixie",
      "Sales": 151.12
  },
  {
      "County": "Duval",
      "Sales": 8398.42
  },
  {
      "County": "Escambia",
      "Sales": 2899.32
  },
  {
      "County": "Flagler",
      "Sales": 947.64
  },
  {
      "County": "Franklin",
      "Sales": 109.96
  },
  {
      "County": "Gadsden",
      "Sales": 437.95
  },
  {
      "County": "Gilchrist",
      "Sales": 160.52
  },
  {
      "County": "Glades",
      "Sales": 126.52
  },
  {
      "County": "Gulf",
      "Sales": 150.07
  },
  {
      "County": "Hamilton",
      "Sales": 136.08
  },
  {
      "County": "Hardee",
      "Sales": 260.90
  },
  {
      "County": "Hendry",
      "Sales": 355.25
  },
  {
      "County": "Hernando",
      "Sales": 1653.80
  },
  {
      "County": "Highlands",
      "Sales": 925.46
  },
  {
      "County": "Hillsborough",
      "Sales": 12244.91
  },
  {
      "County": "Holmes",
      "Sales": 186.93
  },
  {
      "County": "Indian River",
      "Sales": 1346.19
  },
  {
      "County": "Jackson",
      "Sales": 463.81
  },
  {
      "County": "Jefferson",
      "Sales": 134.57
  },
  {
      "County": "Lafayette",
      "Sales": 83.88
  },
  {
      "County": "Lake",
      "Sales": 2920.34
  },
  {
      "County": "Lee",
      "Sales": 6267.75
  },
  {
      "County": "Leon",
      "Sales": 2672.05
  },
  {
      "County": "Levy",
      "Sales": 375.85
  },
  {
      "County": "Liberty",
      "Sales": 79.15
  },
  {
      "County": "Madison",
      "Sales": 177.55
  },
  {
      "County": "Manatee",
      "Sales": 3243.36
  },
  {
      "County": "Marion",
      "Sales": 3198.39
  },
  {
      "County": "Martin",
      "Sales": 1434.06
  },
  {
      "County": "Miami-Dade",
      "Sales": 24812.34
  },
  {
      "County": "Monroe",
      "Sales": 723.85
  },
  {
      "County": "Nassau",
      "Sales": 717.77
  },
  {
      "County": "Okaloosa",
      "Sales": 1837.44
  },
  {
      "County": "Okeechobee",
      "Sales": 372.87
  },
  {
      "County": "Orange",
      "Sales": 11616.24
  },
  {
      "County": "Osceola",
      "Sales": 2829.99
  },
  {
      "County": "Palm Beach",
      "Sales": 13008.98
  },
  {
      "County": "Pasco",
      "Sales": 4508.03
  },
  {
      "County": "Pinellas",
      "Sales": 8807.91
  },
  {
      "County": "Polk",
      "Sales": 5906.49
  },
  {
      "County": "Putnam",
      "Sales": 688.07
  },
  {
      "County": "St. Johns",
      "Sales": 1987.57
  },
  {
      "County": "St. Lucie",
      "Sales": 2719.33
  },
  {
      "County": "Santa Rosa",
      "Sales": 1527.28
  },
  {
      "County": "Sarasota",
      "Sales": 3701.49
  },
  {
      "County": "Seminole",
      "Sales": 4133.92
  },
  {
      "County": "Sumter",
      "Sales": 1014.95
  },
  {
      "County": "Suwannee",
      "Sales": 414.62
  },
  {
      "County": "Taylor",
      "Sales": 216.70
  },
  {
      "County": "Union",
      "Sales": 143.50
  },
  {
      "County": "Volusia",
      "Sales": 4747.87
  },
  {
      "County": "Wakulla",
      "Sales": 294.11
  },
  {
      "County": "Walton",
      "Sales": 567.00
  },
  {
      "County": "Washington",
      "Sales": 233.45
  }
        ];
    }

    if (state == 'GA') {
        items = [
  {
      "County": "Appling",
      "Sales": 166.78
  },
  {
      "County": "Atkinson",
      "Sales": 74.98
  },
  {
      "County": "Bacon",
      "Sales": 101.44
  },
  {
      "County": "Baker",
      "Sales": 30.22
  },
  {
      "County": "Baldwin",
      "Sales": 416.40
  },
  {
      "County": "Banks",
      "Sales": 166.56
  },
  {
      "County": "Barrow",
      "Sales": 646.26
  },
  {
      "County": "Bartow",
      "Sales": 915.97
  },
  {
      "County": "Ben Hill",
      "Sales": 158.41
  },
  {
      "County": "Berrien",
      "Sales": 172.28
  },
  {
      "County": "Bibb",
      "Sales": 1399.38
  },
  {
      "County": "Bleckley",
      "Sales": 115.51
  },
  {
      "County": "Brantley",
      "Sales": 165.44
  },
  {
      "County": "Brooks",
      "Sales": 140.33
  },
  {
      "County": "Bryan",
      "Sales": 299.89
  },
  {
      "County": "Bulloch",
      "Sales": 644.10
  },
  {
      "County": "Burke",
      "Sales": 207.33
  },
  {
      "County": "Butts",
      "Sales": 211.29
  },
  {
      "County": "Calhoun",
      "Sales": 59.00
  },
  {
      "County": "Camden",
      "Sales": 465.58
  },
  {
      "County": "Candler",
      "Sales": 98.92
  },
  {
      "County": "Carroll",
      "Sales": 1016.20
  },
  {
      "County": "Catoosa",
      "Sales": 590.71
  },
  {
      "County": "Charlton",
      "Sales": 119.89
  },
  {
      "County": "Chatham",
      "Sales": 2518.31
  },
  {
      "County": "Chattahoochee",
      "Sales": 116.15
  },
  {
      "County": "Chattooga",
      "Sales": 227.36
  },
  {
      "County": "Cherokee",
      "Sales": 2035.98
  },
  {
      "County": "Clarke",
      "Sales": 1096.79
  },
  {
      "County": "Clay",
      "Sales": 27.54
  },
  {
      "County": "Clayton",
      "Sales": 2389.75
  },
  {
      "County": "Clinch",
      "Sales": 61.46
  },
  {
      "County": "Cobb",
      "Sales": 6486.65
  },
  {
      "County": "Coffee",
      "Sales": 390.90
  },
  {
      "County": "Colquitt",
      "Sales": 418.54
  },
  {
      "County": "Columbia",
      "Sales": 1224.77
  },
  {
      "County": "Cook",
      "Sales": 154.35
  },
  {
      "County": "Coweta",
      "Sales": 1204.55
  },
  {
      "County": "Crawford",
      "Sales": 113.09
  },
  {
      "County": "Crisp",
      "Sales": 211.06
  },
  {
      "County": "Dade",
      "Sales": 149.30
  },
  {
      "County": "Dawson",
      "Sales": 205.18
  },
  {
      "County": "Decatur",
      "Sales": 247.45
  },
  {
      "County": "DeKalb",
      "Sales": 6451.83
  },
  {
      "County": "Dodge",
      "Sales": 191.93
  },
  {
      "County": "Dooly",
      "Sales": 129.37
  },
  {
      "County": "Dougherty",
      "Sales": 840.86
  },
  {
      "County": "Douglas",
      "Sales": 1233.48
  },
  {
      "County": "Early",
      "Sales": 95.35
  },
  {
      "County": "Echols",
      "Sales": 36.69
  },
  {
      "County": "Effingham",
      "Sales": 492.53
  },
  {
      "County": "Elbert",
      "Sales": 177.26
  },
  {
      "County": "Emanuel",
      "Sales": 206.82
  },
  {
      "County": "Evans",
      "Sales": 97.98
  },
  {
      "County": "Fannin",
      "Sales": 214.90
  },
  {
      "County": "Fayette",
      "Sales": 980.11
  },
  {
      "County": "Floyd",
      "Sales": 866.66
  },
  {
      "County": "Forsyth",
      "Sales": 1767.35
  },
  {
      "County": "Franklin",
      "Sales": 199.06
  },
  {
      "County": "Fulton",
      "Sales": 8902.47
  },
  {
      "County": "Gilmer",
      "Sales": 258.48
  },
  {
      "County": "Glascock",
      "Sales": 28.06
  },
  {
      "County": "Glynn",
      "Sales": 737.20
  },
  {
      "County": "Gordon",
      "Sales": 504.30
  },
  {
      "County": "Grady",
      "Sales": 228.63
  },
  {
      "County": "Greene",
      "Sales": 147.62
  },
  {
      "County": "Gwinnett",
      "Sales": 7772.00
  },
  {
      "County": "Habersham",
      "Sales": 391.63
  },
  {
      "County": "Hall",
      "Sales": 1698.07
  },
  {
      "County": "Hancock",
      "Sales": 80.31
  },
  {
      "County": "Haralson",
      "Sales": 257.72
  },
  {
      "County": "Harris",
      "Sales": 295.42
  },
  {
      "County": "Hart",
      "Sales": 230.15
  },
  {
      "County": "Heard",
      "Sales": 104.54
  },
  {
      "County": "Henry",
      "Sales": 1909.55
  },
  {
      "County": "Houston",
      "Sales": 1335.50
  },
  {
      "County": "Irwin",
      "Sales": 85.26
  },
  {
      "County": "Jackson",
      "Sales": 552.11
  },
  {
      "County": "Jasper",
      "Sales": 123.01
  },
  {
      "County": "Jeff Davis",
      "Sales": 135.70
  },
  {
      "County": "Jefferson",
      "Sales": 147.61
  },
  {
      "County": "Jenkins",
      "Sales": 83.83
  },
  {
      "County": "Johnson",
      "Sales": 88.34
  },
  {
      "County": "Jones",
      "Sales": 258.39
  },
  {
      "County": "Lamar",
      "Sales": 162.43
  },
  {
      "County": "Lanier",
      "Sales": 94.14
  },
  {
      "County": "Laurens",
      "Sales": 434.13
  },
  {
      "County": "Lee",
      "Sales": 262.93
  },
  {
      "County": "Liberty",
      "Sales": 580.07
  },
  {
      "County": "Lincoln",
      "Sales": 70.10
  },
  {
      "County": "Long",
      "Sales": 150.36
  },
  {
      "County": "Lowndes",
      "Sales": 1021.27
  },
  {
      "County": "Lumpkin",
      "Sales": 279.64
  },
  {
      "County": "McDuffie",
      "Sales": 195.05
  },
  {
      "County": "McIntosh",
      "Sales": 126.69
  },
  {
      "County": "Macon",
      "Sales": 126.70
  },
  {
      "County": "Madison",
      "Sales": 253.76
  },
  {
      "County": "Marion",
      "Sales": 78.14
  },
  {
      "County": "Meriwether",
      "Sales": 192.03
  },
  {
      "County": "Miller",
      "Sales": 53.65
  },
  {
      "County": "Mitchell",
      "Sales": 208.43
  },
  {
      "County": "Monroe",
      "Sales": 244.06
  },
  {
      "County": "Montgomery",
      "Sales": 81.59
  },
  {
      "County": "Morgan",
      "Sales": 160.82
  },
  {
      "County": "Murray",
      "Sales": 355.15
  },
  {
      "County": "Muscogee",
      "Sales": 1834.45
  },
  {
      "County": "Newton",
      "Sales": 926.58
  },
  {
      "County": "Oconee",
      "Sales": 307.83
  },
  {
      "County": "Oglethorpe",
      "Sales": 131.58
  },
  {
      "County": "Paulding",
      "Sales": 1329.09
  },
  {
      "County": "Peach",
      "Sales": 244.33
  },
  {
      "County": "Pickens",
      "Sales": 267.57
  },
  {
      "County": "Pierce",
      "Sales": 171.29
  },
  {
      "County": "Pike",
      "Sales": 160.96
  },
  {
      "County": "Polk",
      "Sales": 372.48
  },
  {
      "County": "Pulaski",
      "Sales": 104.39
  },
  {
      "County": "Putnam",
      "Sales": 193.29
  },
  {
      "County": "Quitman",
      "Sales": 21.41
  },
  {
      "County": "Rabun",
      "Sales": 146.84
  },
  {
      "County": "Randolph",
      "Sales": 65.09
  },
  {
      "County": "Richmond",
      "Sales": 1827.02
  },
  {
      "County": "Rockdale",
      "Sales": 786.14
  },
  {
      "County": "Schley",
      "Sales": 46.03
  },
  {
      "County": "Screven",
      "Sales": 128.79
  },
  {
      "County": "Seminole",
      "Sales": 80.90
  },
  {
      "County": "Spalding",
      "Sales": 577.30
  },
  {
      "County": "Stephens",
      "Sales": 232.29
  },
  {
      "County": "Stewart",
      "Sales": 53.07
  },
  {
      "County": "Sumter",
      "Sales": 283.67
  },
  {
      "County": "Talbot",
      "Sales": 58.39
  },
  {
      "County": "Taliaferro",
      "Sales": 15.40
  },
  {
      "County": "Tattnall",
      "Sales": 230.87
  },
  {
      "County": "Taylor",
      "Sales": 76.55
  },
  {
      "County": "Telfair",
      "Sales": 150.06
  },
  {
      "County": "Terrell",
      "Sales": 81.60
  },
  {
      "County": "Thomas",
      "Sales": 405.82
  },
  {
      "County": "Tift",
      "Sales": 364.37
  },
  {
      "County": "Toombs",
      "Sales": 246.67
  },
  {
      "County": "Towns",
      "Sales": 97.42
  },
  {
      "County": "Treutlen",
      "Sales": 60.71
  },
  {
      "County": "Troup",
      "Sales": 624.55
  },
  {
      "County": "Turner",
      "Sales": 73.57
  },
  {
      "County": "Twiggs",
      "Sales": 76.71
  },
  {
      "County": "Union",
      "Sales": 195.05
  },
  {
      "County": "Upson",
      "Sales": 240.28
  },
  {
      "County": "Walker",
      "Sales": 616.82
  },
  {
      "County": "Walton",
      "Sales": 775.60
  },
  {
      "County": "Ware",
      "Sales": 322.97
  },
  {
      "County": "Warren",
      "Sales": 50.27
  },
  {
      "County": "Washington",
      "Sales": 187.00
  },
  {
      "County": "Wayne",
      "Sales": 272.03
  },
  {
      "County": "Webster",
      "Sales": 24.59
  },
  {
      "County": "Wheeler",
      "Sales": 71.53
  },
  {
      "County": "White",
      "Sales": 251.41
  },
  {
      "County": "Whitfield",
      "Sales": 931.09
  },
  {
      "County": "Wilcox",
      "Sales": 81.04
  },
  {
      "County": "Wilkes",
      "Sales": 90.54
  },
  {
      "County": "Wilkinson",
      "Sales": 85.31
  },
  {
      "County": "Worth",
      "Sales": 192.57
  }
        ];
    }

    if (state == 'HI') {
        items = [
  {
      "County": "Hawaii",
      "Sales": 1151.41
  },
  {
      "County": "Honolulu",
      "Sales": 5934.01
  },
  {
      "County": "Kalawao",
      "Sales": 0.54
  },
  {
      "County": "Kauai",
      "Sales": 419.44
  },
  {
      "County": "Maui",
      "Sales": 966.66
  }
        ];
    }

    if (state == 'IA') {
        items = [
  {
      "County": "Adair",
      "Sales": 95.65
  },
  {
      "County": "Adams",
      "Sales": 49.85
  },
  {
      "County": "Allamakee",
      "Sales": 181.37
  },
  {
      "County": "Appanoose",
      "Sales": 162.46
  },
  {
      "County": "Audubon",
      "Sales": 75.18
  },
  {
      "County": "Benton",
      "Sales": 328.96
  },
  {
      "County": "Black Hawk",
      "Sales": 1696.67
  },
  {
      "County": "Boone",
      "Sales": 337.47
  },
  {
      "County": "Bremer",
      "Sales": 315.20
  },
  {
      "County": "Buchanan",
      "Sales": 268.50
  },
  {
      "County": "Buena Vista",
      "Sales": 263.27
  },
  {
      "County": "Butler",
      "Sales": 192.28
  },
  {
      "County": "Calhoun",
      "Sales": 127.06
  },
  {
      "County": "Carroll",
      "Sales": 263.67
  },
  {
      "County": "Cass",
      "Sales": 174.06
  },
  {
      "County": "Cedar",
      "Sales": 235.44
  },
  {
      "County": "Cerro Gordo",
      "Sales": 557.79
  },
  {
      "County": "Cherokee",
      "Sales": 152.90
  },
  {
      "County": "Chickasaw",
      "Sales": 157.72
  },
  {
      "County": "Clarke",
      "Sales": 119.37
  },
  {
      "County": "Clay",
      "Sales": 211.09
  },
  {
      "County": "Clayton",
      "Sales": 227.50
  },
  {
      "County": "Clinton",
      "Sales": 619.80
  },
  {
      "County": "Crawford",
      "Sales": 223.17
  },
  {
      "County": "Dallas",
      "Sales": 955.45
  },
  {
      "County": "Davis",
      "Sales": 112.53
  },
  {
      "County": "Decatur",
      "Sales": 104.15
  },
  {
      "County": "Delaware",
      "Sales": 224.45
  },
  {
      "County": "Des Moines",
      "Sales": 518.17
  },
  {
      "County": "Dickinson",
      "Sales": 217.03
  },
  {
      "County": "Dubuque",
      "Sales": 1224.98
  },
  {
      "County": "Emmet",
      "Sales": 127.95
  },
  {
      "County": "Fayette",
      "Sales": 262.44
  },
  {
      "County": "Floyd",
      "Sales": 205.99
  },
  {
      "County": "Franklin",
      "Sales": 135.02
  },
  {
      "County": "Fremont",
      "Sales": 90.63
  },
  {
      "County": "Greene",
      "Sales": 116.98
  },
  {
      "County": "Grundy",
      "Sales": 157.63
  },
  {
      "County": "Guthrie",
      "Sales": 136.80
  },
  {
      "County": "Hamilton",
      "Sales": 196.00
  },
  {
      "County": "Hancock",
      "Sales": 142.01
  },
  {
      "County": "Hardin",
      "Sales": 223.25
  },
  {
      "County": "Harrison",
      "Sales": 184.73
  },
  {
      "County": "Henry",
      "Sales": 258.85
  },
  {
      "County": "Howard",
      "Sales": 121.94
  },
  {
      "County": "Humboldt",
      "Sales": 124.01
  },
  {
      "County": "Ida",
      "Sales": 91.41
  },
  {
      "County": "Iowa",
      "Sales": 209.03
  },
  {
      "County": "Jackson",
      "Sales": 250.72
  },
  {
      "County": "Jasper",
      "Sales": 469.03
  },
  {
      "County": "Jefferson",
      "Sales": 215.18
  },
  {
      "County": "Johnson",
      "Sales": 1781.26
  },
  {
      "County": "Jones",
      "Sales": 263.83
  },
  {
      "County": "Keokuk",
      "Sales": 132.22
  },
  {
      "County": "Kossuth",
      "Sales": 196.12
  },
  {
      "County": "Lee",
      "Sales": 456.75
  },
  {
      "County": "Linn",
      "Sales": 2766.35
  },
  {
      "County": "Louisa",
      "Sales": 144.42
  },
  {
      "County": "Lucas",
      "Sales": 111.95
  },
  {
      "County": "Lyon",
      "Sales": 149.92
  },
  {
      "County": "Madison",
      "Sales": 197.74
  },
  {
      "County": "Mahaska",
      "Sales": 286.95
  },
  {
      "County": "Marion",
      "Sales": 425.64
  },
  {
      "County": "Marshall",
      "Sales": 524.75
  },
  {
      "County": "Mills",
      "Sales": 190.68
  },
  {
      "County": "Mitchell",
      "Sales": 137.08
  },
  {
      "County": "Monona",
      "Sales": 116.75
  },
  {
      "County": "Monroe",
      "Sales": 102.56
  },
  {
      "County": "Montgomery",
      "Sales": 133.43
  },
  {
      "County": "Muscatine",
      "Sales": 548.33
  },
  {
      "County": "O'Brien",
      "Sales": 179.77
  },
  {
      "County": "Osceola",
      "Sales": 79.50
  },
  {
      "County": "Page",
      "Sales": 201.14
  },
  {
      "County": "Palo Alto",
      "Sales": 117.57
  },
  {
      "County": "Plymouth",
      "Sales": 319.46
  },
  {
      "County": "Pocahontas",
      "Sales": 91.58
  },
  {
      "County": "Polk",
      "Sales": 5781.73
  },
  {
      "County": "Pottawattamie",
      "Sales": 1186.97
  },
  {
      "County": "Poweshiek",
      "Sales": 238.10
  },
  {
      "County": "Ringgold",
      "Sales": 64.92
  },
  {
      "County": "Sac",
      "Sales": 128.91
  },
  {
      "County": "Scott",
      "Sales": 2181.03
  },
  {
      "County": "Shelby",
      "Sales": 153.11
  },
  {
      "County": "Sioux",
      "Sales": 442.22
  },
  {
      "County": "Story",
      "Sales": 1182.85
  },
  {
      "County": "Tama",
      "Sales": 224.98
  },
  {
      "County": "Taylor",
      "Sales": 78.86
  },
  {
      "County": "Union",
      "Sales": 161.07
  },
  {
      "County": "Van Buren",
      "Sales": 95.19
  },
  {
      "County": "Wapello",
      "Sales": 453.03
  },
  {
      "County": "Warren",
      "Sales": 605.93
  },
  {
      "County": "Washington",
      "Sales": 281.80
  },
  {
      "County": "Wayne",
      "Sales": 81.95
  },
  {
      "County": "Webster",
      "Sales": 474.18
  },
  {
      "County": "Winnebago",
      "Sales": 135.10
  },
  {
      "County": "Winneshiek",
      "Sales": 268.74
  },
  {
      "County": "Woodbury",
      "Sales": 1307.32
  },
  {
      "County": "Worth",
      "Sales": 96.53
  },
  {
      "County": "Wright",
      "Sales": 166.05
  }
        ];
    }

    if (state == 'ID') {
        items = [
  {
      "County": "Ada",
      "Sales": 4045.68
  },
  {
      "County": "Adams",
      "Sales": 37.19
  },
  {
      "County": "Bannock",
      "Sales": 808.71
  },
  {
      "County": "Bear Lake",
      "Sales": 57.73
  },
  {
      "County": "Benewah",
      "Sales": 87.86
  },
  {
      "County": "Bingham",
      "Sales": 439.96
  },
  {
      "County": "Blaine",
      "Sales": 207.20
  },
  {
      "County": "Boise",
      "Sales": 66.01
  },
  {
      "County": "Bonner",
      "Sales": 395.37
  },
  {
      "County": "Bonneville",
      "Sales": 1044.46
  },
  {
      "County": "Boundary",
      "Sales": 105.43
  },
  {
      "County": "Butte",
      "Sales": 25.67
  },
  {
      "County": "Camas",
      "Sales": 10.12
  },
  {
      "County": "Canyon",
      "Sales": 1931.91
  },
  {
      "County": "Caribou",
      "Sales": 66.14
  },
  {
      "County": "Cassia",
      "Sales": 226.65
  },
  {
      "County": "Clark",
      "Sales": 8.42
  },
  {
      "County": "Clearwater",
      "Sales": 83.32
  },
  {
      "County": "Custer",
      "Sales": 41.28
  },
  {
      "County": "Elmore",
      "Sales": 254.22
  },
  {
      "County": "Franklin",
      "Sales": 124.87
  },
  {
      "County": "Fremont",
      "Sales": 125.58
  },
  {
      "County": "Gem",
      "Sales": 162.09
  },
  {
      "County": "Gooding",
      "Sales": 146.49
  },
  {
      "County": "Idaho",
      "Sales": 156.56
  },
  {
      "County": "Jefferson",
      "Sales": 261.45
  },
  {
      "County": "Jerome",
      "Sales": 218.71
  },
  {
      "County": "Kootenai",
      "Sales": 1401.44
  },
  {
      "County": "Latah",
      "Sales": 369.90
  },
  {
      "County": "Lemhi",
      "Sales": 74.92
  },
  {
      "County": "Lewis",
      "Sales": 37.91
  },
  {
      "County": "Lincoln",
      "Sales": 51.63
  },
  {
      "County": "Madison",
      "Sales": 363.80
  },
  {
      "County": "Minidoka",
      "Sales": 197.12
  },
  {
      "County": "Nez Perce",
      "Sales": 387.75
  },
  {
      "County": "Oneida",
      "Sales": 41.53
  },
  {
      "County": "Owyhee",
      "Sales": 111.44
  },
  {
      "County": "Payette",
      "Sales": 219.64
  },
  {
      "County": "Power",
      "Sales": 74.99
  },
  {
      "County": "Shoshone",
      "Sales": 123.28
  },
  {
      "County": "Teton",
      "Sales": 99.82
  },
  {
      "County": "Twin Falls",
      "Sales": 776.73
  },
  {
      "County": "Valley",
      "Sales": 93.32
  },
  {
      "County": "Washington",
      "Sales": 96.60
  }
        ];
    }

    if (state == 'IL') {
        items = [
  {
      "County": "Adams",
      "Sales": 495.73
  },
  {
      "County": "Alexander",
      "Sales": 56.34
  },
  {
      "County": "Bond",
      "Sales": 129.01
  },
  {
      "County": "Boone",
      "Sales": 398.45
  },
  {
      "County": "Brown",
      "Sales": 50.66
  },
  {
      "County": "Bureau",
      "Sales": 251.49
  },
  {
      "County": "Calhoun",
      "Sales": 37.36
  },
  {
      "County": "Carroll",
      "Sales": 110.10
  },
  {
      "County": "Cass",
      "Sales": 98.39
  },
  {
      "County": "Champaign",
      "Sales": 1513.09
  },
  {
      "County": "Christian",
      "Sales": 253.28
  },
  {
      "County": "Clark",
      "Sales": 119.50
  },
  {
      "County": "Clay",
      "Sales": 100.18
  },
  {
      "County": "Clinton",
      "Sales": 279.93
  },
  {
      "County": "Coles",
      "Sales": 396.53
  },
  {
      "County": "Cook",
      "Sales": 38700.64
  },
  {
      "County": "Crawford",
      "Sales": 144.04
  },
  {
      "County": "Cumberland",
      "Sales": 80.78
  },
  {
      "County": "DeKalb",
      "Sales": 773.47
  },
  {
      "County": "De Witt",
      "Sales": 121.26
  },
  {
      "County": "Douglas",
      "Sales": 146.86
  },
  {
      "County": "DuPage",
      "Sales": 6883.41
  },
  {
      "County": "Edgar",
      "Sales": 132.63
  },
  {
      "County": "Edwards",
      "Sales": 49.27
  },
  {
      "County": "Effingham",
      "Sales": 253.34
  },
  {
      "County": "Fayette",
      "Sales": 162.90
  },
  {
      "County": "Ford",
      "Sales": 102.14
  },
  {
      "County": "Franklin",
      "Sales": 289.49
  },
  {
      "County": "Fulton",
      "Sales": 268.40
  },
  {
      "County": "Gallatin",
      "Sales": 39.99
  },
  {
      "County": "Greene",
      "Sales": 100.65
  },
  {
      "County": "Grundy",
      "Sales": 370.92
  },
  {
      "County": "Hamilton",
      "Sales": 61.79
  },
  {
      "County": "Hancock",
      "Sales": 137.49
  },
  {
      "County": "Hardin",
      "Sales": 30.88
  },
  {
      "County": "Henderson",
      "Sales": 51.62
  },
  {
      "County": "Henry",
      "Sales": 368.20
  },
  {
      "County": "Iroquois",
      "Sales": 214.02
  },
  {
      "County": "Jackson",
      "Sales": 441.70
  },
  {
      "County": "Jasper",
      "Sales": 70.83
  },
  {
      "County": "Jefferson",
      "Sales": 285.37
  },
  {
      "County": "Jersey",
      "Sales": 167.20
  },
  {
      "County": "Jo Daviess",
      "Sales": 165.47
  },
  {
      "County": "Johnson",
      "Sales": 93.61
  },
  {
      "County": "Kane",
      "Sales": 3866.91
  },
  {
      "County": "Kankakee",
      "Sales": 827.96
  },
  {
      "County": "Kendall",
      "Sales": 881.34
  },
  {
      "County": "Knox",
      "Sales": 384.58
  },
  {
      "County": "Lake",
      "Sales": 5191.54
  },
  {
      "County": "LaSalle",
      "Sales": 828.43
  },
  {
      "County": "Lawrence",
      "Sales": 122.27
  },
  {
      "County": "Lee",
      "Sales": 257.41
  },
  {
      "County": "Livingston",
      "Sales": 281.99
  },
  {
      "County": "Logan",
      "Sales": 221.27
  },
  {
      "County": "McDonough",
      "Sales": 239.73
  },
  {
      "County": "McHenry",
      "Sales": 2270.10
  },
  {
      "County": "McLean",
      "Sales": 1289.70
  },
  {
      "County": "Macon",
      "Sales": 806.98
  },
  {
      "County": "Macoupin",
      "Sales": 346.19
  },
  {
      "County": "Madison",
      "Sales": 1973.36
  },
  {
      "County": "Marion",
      "Sales": 285.21
  },
  {
      "County": "Marshall",
      "Sales": 89.75
  },
  {
      "County": "Mason",
      "Sales": 105.22
  },
  {
      "County": "Massac",
      "Sales": 111.31
  },
  {
      "County": "Menard",
      "Sales": 93.10
  },
  {
      "County": "Mercer",
      "Sales": 119.47
  },
  {
      "County": "Monroe",
      "Sales": 247.33
  },
  {
      "County": "Montgomery",
      "Sales": 218.98
  },
  {
      "County": "Morgan",
      "Sales": 258.96
  },
  {
      "County": "Moultrie",
      "Sales": 109.85
  },
  {
      "County": "Ogle",
      "Sales": 386.84
  },
  {
      "County": "Peoria",
      "Sales": 1391.48
  },
  {
      "County": "Perry",
      "Sales": 161.63
  },
  {
      "County": "Piatt",
      "Sales": 121.35
  },
  {
      "County": "Pike",
      "Sales": 119.26
  },
  {
      "County": "Pope",
      "Sales": 31.84
  },
  {
      "County": "Pulaski",
      "Sales": 43.63
  },
  {
      "County": "Putnam",
      "Sales": 42.84
  },
  {
      "County": "Randolph",
      "Sales": 242.88
  },
  {
      "County": "Richland",
      "Sales": 119.50
  },
  {
      "County": "Rock Island",
      "Sales": 1087.45
  },
  {
      "County": "St. Clair",
      "Sales": 1971.36
  },
  {
      "County": "Saline",
      "Sales": 184.17
  },
  {
      "County": "Sangamon",
      "Sales": 1470.61
  },
  {
      "County": "Schuyler",
      "Sales": 54.97
  },
  {
      "County": "Scott",
      "Sales": 38.56
  },
  {
      "County": "Shelby",
      "Sales": 163.34
  },
  {
      "County": "Stark",
      "Sales": 43.62
  },
  {
      "County": "Stephenson",
      "Sales": 345.16
  },
  {
      "County": "Tazewell",
      "Sales": 1006.91
  },
  {
      "County": "Union",
      "Sales": 129.84
  },
  {
      "County": "Vermilion",
      "Sales": 593.20
  },
  {
      "County": "Wabash",
      "Sales": 86.14
  },
  {
      "County": "Warren",
      "Sales": 130.90
  },
  {
      "County": "Washington",
      "Sales": 106.69
  },
  {
      "County": "Wayne",
      "Sales": 122.67
  },
  {
      "County": "White",
      "Sales": 107.44
  },
  {
      "County": "Whiteside",
      "Sales": 425.04
  },
  {
      "County": "Will",
      "Sales": 5042.44
  },
  {
      "County": "Williamson",
      "Sales": 494.21
  },
  {
      "County": "Winnebago",
      "Sales": 2146.46
  },
  {
      "County": "Woodford",
      "Sales": 290.02
  }
        ];
    }

    if (state == 'IN') {
        items = [
  {
      "County": "Adams",
      "Sales": 300.39
  },
  {
      "County": "Allen",
      "Sales": 3150.33
  },
  {
      "County": "Bartholomew",
      "Sales": 690.68
  },
  {
      "County": "Benton",
      "Sales": 76.08
  },
  {
      "County": "Blackford",
      "Sales": 108.31
  },
  {
      "County": "Boone",
      "Sales": 524.84
  },
  {
      "County": "Brown",
      "Sales": 130.37
  },
  {
      "County": "Carroll",
      "Sales": 174.31
  },
  {
      "County": "Cass",
      "Sales": 333.79
  },
  {
      "County": "Clark",
      "Sales": 980.11
  },
  {
      "County": "Clay",
      "Sales": 232.60
  },
  {
      "County": "Clinton",
      "Sales": 285.65
  },
  {
      "County": "Crawford",
      "Sales": 92.17
  },
  {
      "County": "Daviess",
      "Sales": 281.24
  },
  {
      "County": "Dearborn",
      "Sales": 433.08
  },
  {
      "County": "Decatur",
      "Sales": 228.04
  },
  {
      "County": "DeKalb",
      "Sales": 367.15
  },
  {
      "County": "Delaware",
      "Sales": 1019.56
  },
  {
      "County": "Dubois",
      "Sales": 367.62
  },
  {
      "County": "Elkhart",
      "Sales": 1740.54
  },
  {
      "County": "Fayette",
      "Sales": 207.07
  },
  {
      "County": "Floyd",
      "Sales": 661.67
  },
  {
      "County": "Fountain",
      "Sales": 146.49
  },
  {
      "County": "Franklin",
      "Sales": 199.18
  },
  {
      "County": "Fulton",
      "Sales": 177.46
  },
  {
      "County": "Gibson",
      "Sales": 291.69
  },
  {
      "County": "Grant",
      "Sales": 599.89
  },
  {
      "County": "Greene",
      "Sales": 284.48
  },
  {
      "County": "Hamilton",
      "Sales": 2574.78
  },
  {
      "County": "Hancock",
      "Sales": 621.15
  },
  {
      "County": "Harrison",
      "Sales": 339.87
  },
  {
      "County": "Hendricks",
      "Sales": 1335.40
  },
  {
      "County": "Henry",
      "Sales": 425.62
  },
  {
      "County": "Howard",
      "Sales": 718.21
  },
  {
      "County": "Huntington",
      "Sales": 319.28
  },
  {
      "County": "Jackson",
      "Sales": 377.21
  },
  {
      "County": "Jasper",
      "Sales": 289.76
  },
  {
      "County": "Jay",
      "Sales": 185.11
  },
  {
      "County": "Jefferson",
      "Sales": 281.68
  },
  {
      "County": "Jennings",
      "Sales": 245.08
  },
  {
      "County": "Johnson",
      "Sales": 1262.99
  },
  {
      "County": "Knox",
      "Sales": 329.38
  },
  {
      "County": "Kosciusko",
      "Sales": 676.58
  },
  {
      "County": "LaGrange",
      "Sales": 329.74
  },
  {
      "County": "Lake",
      "Sales": 4264.99
  },
  {
      "County": "LaPorte",
      "Sales": 965.73
  },
  {
      "County": "Lawrence",
      "Sales": 397.85
  },
  {
      "County": "Madison",
      "Sales": 1132.36
  },
  {
      "County": "Marion",
      "Sales": 8055.88
  },
  {
      "County": "Marshall",
      "Sales": 408.82
  },
  {
      "County": "Martin",
      "Sales": 88.17
  },
  {
      "County": "Miami",
      "Sales": 313.63
  },
  {
      "County": "Monroe",
      "Sales": 1231.34
  },
  {
      "County": "Montgomery",
      "Sales": 331.31
  },
  {
      "County": "Morgan",
      "Sales": 605.59
  },
  {
      "County": "Newton",
      "Sales": 122.25
  },
  {
      "County": "Noble",
      "Sales": 412.83
  },
  {
      "County": "Ohio",
      "Sales": 52.02
  },
  {
      "County": "Orange",
      "Sales": 171.60
  },
  {
      "County": "Owen",
      "Sales": 183.99
  },
  {
      "County": "Parke",
      "Sales": 149.28
  },
  {
      "County": "Perry",
      "Sales": 169.73
  },
  {
      "County": "Pike",
      "Sales": 110.07
  },
  {
      "County": "Porter",
      "Sales": 1445.43
  },
  {
      "County": "Posey",
      "Sales": 221.17
  },
  {
      "County": "Pulaski",
      "Sales": 112.88
  },
  {
      "County": "Putnam",
      "Sales": 325.48
  },
  {
      "County": "Randolph",
      "Sales": 222.40
  },
  {
      "County": "Ripley",
      "Sales": 246.63
  },
  {
      "County": "Rush",
      "Sales": 147.57
  },
  {
      "County": "St. Joseph",
      "Sales": 2314.57
  },
  {
      "County": "Scott",
      "Sales": 208.04
  },
  {
      "County": "Shelby",
      "Sales": 388.17
  },
  {
      "County": "Spencer",
      "Sales": 181.76
  },
  {
      "County": "Starke",
      "Sales": 201.31
  },
  {
      "County": "Steuben",
      "Sales": 298.17
  },
  {
      "County": "Sullivan",
      "Sales": 184.18
  },
  {
      "County": "Switzerland",
      "Sales": 91.35
  },
  {
      "County": "Tippecanoe",
      "Sales": 1563.60
  },
  {
      "County": "Tipton",
      "Sales": 135.81
  },
  {
      "County": "Union",
      "Sales": 63.15
  },
  {
      "County": "Vanderburgh",
      "Sales": 1574.22
  },
  {
      "County": "Vermillion",
      "Sales": 137.79
  },
  {
      "County": "Vigo",
      "Sales": 939.78
  },
  {
      "County": "Wabash",
      "Sales": 280.81
  },
  {
      "County": "Warren",
      "Sales": 73.03
  },
  {
      "County": "Warrick",
      "Sales": 529.80
  },
  {
      "County": "Washington",
      "Sales": 241.08
  },
  {
      "County": "Wayne",
      "Sales": 589.19
  },
  {
      "County": "Wells",
      "Sales": 241.38
  },
  {
      "County": "White",
      "Sales": 212.32
  },
  {
      "County": "Whitley",
      "Sales": 288.93
  }
        ];
    }

    if (state == 'KS') {
        items = [
  {
      "County": "Allen",
      "Sales": 152.98
  },
  {
      "County": "Anderson",
      "Sales": 92.05
  },
  {
      "County": "Atchison",
      "Sales": 195.23
  },
  {
      "County": "Barber",
      "Sales": 57.66
  },
  {
      "County": "Barton",
      "Sales": 320.66
  },
  {
      "County": "Bourbon",
      "Sales": 173.12
  },
  {
      "County": "Brown",
      "Sales": 116.53
  },
  {
      "County": "Butler",
      "Sales": 767.03
  },
  {
      "County": "Chase",
      "Sales": 31.47
  },
  {
      "County": "Chautauqua",
      "Sales": 41.40
  },
  {
      "County": "Cherokee",
      "Sales": 244.53
  },
  {
      "County": "Cheyenne",
      "Sales": 31.40
  },
  {
      "County": "Clark",
      "Sales": 25.56
  },
  {
      "County": "Clay",
      "Sales": 97.98
  },
  {
      "County": "Cloud",
      "Sales": 108.31
  },
  {
      "County": "Coffey",
      "Sales": 98.05
  },
  {
      "County": "Comanche",
      "Sales": 22.79
  },
  {
      "County": "Cowley",
      "Sales": 422.01
  },
  {
      "County": "Crawford",
      "Sales": 457.84
  },
  {
      "County": "Decatur",
      "Sales": 34.15
  },
  {
      "County": "Dickinson",
      "Sales": 228.57
  },
  {
      "County": "Doniphan",
      "Sales": 91.51
  },
  {
      "County": "Douglas",
      "Sales": 1332.58
  },
  {
      "County": "Edwards",
      "Sales": 34.33
  },
  {
      "County": "Elk",
      "Sales": 30.95
  },
  {
      "County": "Ellis",
      "Sales": 338.75
  },
  {
      "County": "Ellsworth",
      "Sales": 74.58
  },
  {
      "County": "Finney",
      "Sales": 432.43
  },
  {
      "County": "Ford",
      "Sales": 405.86
  },
  {
      "County": "Franklin",
      "Sales": 300.04
  },
  {
      "County": "Geary",
      "Sales": 435.76
  },
  {
      "County": "Gove",
      "Sales": 32.28
  },
  {
      "County": "Graham",
      "Sales": 30.23
  },
  {
      "County": "Grant",
      "Sales": 92.67
  },
  {
      "County": "Gray",
      "Sales": 70.04
  },
  {
      "County": "Greeley",
      "Sales": 15.04
  },
  {
      "County": "Greenwood",
      "Sales": 74.88
  },
  {
      "County": "Hamilton",
      "Sales": 30.41
  },
  {
      "County": "Harper",
      "Sales": 68.31
  },
  {
      "County": "Harvey",
      "Sales": 404.96
  },
  {
      "County": "Haskell",
      "Sales": 48.27
  },
  {
      "County": "Hodgeman",
      "Sales": 22.73
  },
  {
      "County": "Jackson",
      "Sales": 155.80
  },
  {
      "County": "Jefferson",
      "Sales": 219.29
  },
  {
      "County": "Jewell",
      "Sales": 35.51
  },
  {
      "County": "Johnson",
      "Sales": 6608.41
  },
  {
      "County": "Kearny",
      "Sales": 45.73
  },
  {
      "County": "Kingman",
      "Sales": 91.43
  },
  {
      "County": "Kiowa",
      "Sales": 29.41
  },
  {
      "County": "Labette",
      "Sales": 243.81
  },
  {
      "County": "Lane",
      "Sales": 20.05
  },
  {
      "County": "Leavenworth",
      "Sales": 911.36
  },
  {
      "County": "Lincoln",
      "Sales": 36.68
  },
  {
      "County": "Linn",
      "Sales": 110.92
  },
  {
      "County": "Logan",
      "Sales": 32.61
  },
  {
      "County": "Lyon",
      "Sales": 390.61
  },
  {
      "County": "McPherson",
      "Sales": 344.67
  },
  {
      "County": "Marion",
      "Sales": 142.43
  },
  {
      "County": "Marshall",
      "Sales": 116.59
  },
  {
      "County": "Meade",
      "Sales": 50.62
  },
  {
      "County": "Miami",
      "Sales": 382.74
  },
  {
      "County": "Mitchell",
      "Sales": 74.34
  },
  {
      "County": "Montgomery",
      "Sales": 399.72
  },
  {
      "County": "Morris",
      "Sales": 66.92
  },
  {
      "County": "Morton",
      "Sales": 36.64
  },
  {
      "County": "Nemaha",
      "Sales": 118.44
  },
  {
      "County": "Neosho",
      "Sales": 191.51
  },
  {
      "County": "Ness",
      "Sales": 35.82
  },
  {
      "County": "Norton",
      "Sales": 65.53
  },
  {
      "County": "Osage",
      "Sales": 188.16
  },
  {
      "County": "Osborne",
      "Sales": 44.50
  },
  {
      "County": "Ottawa",
      "Sales": 70.43
  },
  {
      "County": "Pawnee",
      "Sales": 81.26
  },
  {
      "County": "Phillips",
      "Sales": 64.58
  },
  {
      "County": "Pottawatomie",
      "Sales": 264.50
  },
  {
      "County": "Pratt",
      "Sales": 115.14
  },
  {
      "County": "Rawlins",
      "Sales": 30.18
  },
  {
      "County": "Reno",
      "Sales": 748.23
  },
  {
      "County": "Republic",
      "Sales": 56.18
  },
  {
      "County": "Rice",
      "Sales": 116.69
  },
  {
      "County": "Riley",
      "Sales": 878.82
  },
  {
      "County": "Rooks",
      "Sales": 60.50
  },
  {
      "County": "Rush",
      "Sales": 37.14
  },
  {
      "County": "Russell",
      "Sales": 80.81
  },
  {
      "County": "Saline",
      "Sales": 649.73
  },
  {
      "County": "Scott",
      "Sales": 58.69
  },
  {
      "County": "Sedgwick",
      "Sales": 5891.33
  },
  {
      "County": "Seward",
      "Sales": 272.64
  },
  {
      "County": "Shawnee",
      "Sales": 2084.53
  },
  {
      "County": "Sheridan",
      "Sales": 29.76
  },
  {
      "County": "Sherman",
      "Sales": 71.28
  },
  {
      "County": "Smith",
      "Sales": 43.20
  },
  {
      "County": "Stafford",
      "Sales": 50.81
  },
  {
      "County": "Stanton",
      "Sales": 25.57
  },
  {
      "County": "Stevens",
      "Sales": 67.79
  },
  {
      "County": "Sumner",
      "Sales": 274.99
  },
  {
      "County": "Thomas",
      "Sales": 92.65
  },
  {
      "County": "Trego",
      "Sales": 34.74
  },
  {
      "County": "Wabaunsee",
      "Sales": 82.19
  },
  {
      "County": "Wallace",
      "Sales": 18.29
  },
  {
      "County": "Washington",
      "Sales": 65.61
  },
  {
      "County": "Wichita",
      "Sales": 25.55
  },
  {
      "County": "Wilson",
      "Sales": 106.13
  },
  {
      "County": "Woodson",
      "Sales": 37.55
  },
  {
      "County": "Wyandotte",
      "Sales": 1869.50
  }
        ];
    }

    if (state == 'KY') {
        items = [
  {
      "County": "Adair",
      "Sales": 95.70
  },
  {
      "County": "Allen",
      "Sales": 103.76
  },
  {
      "County": "Anderson",
      "Sales": 111.43
  },
  {
      "County": "Ballard",
      "Sales": 42.57
  },
  {
      "County": "Barren",
      "Sales": 219.81
  },
  {
      "County": "Bath",
      "Sales": 61.11
  },
  {
      "County": "Bell",
      "Sales": 142.46
  },
  {
      "County": "Boone",
      "Sales": 635.74
  },
  {
      "County": "Bourbon",
      "Sales": 102.16
  },
  {
      "County": "Boyd",
      "Sales": 249.75
  },
  {
      "County": "Boyle",
      "Sales": 148.22
  },
  {
      "County": "Bracken",
      "Sales": 43.00
  },
  {
      "County": "Breathitt",
      "Sales": 69.20
  },
  {
      "County": "Breckinridge",
      "Sales": 102.38
  },
  {
      "County": "Bullitt",
      "Sales": 392.63
  },
  {
      "County": "Butler",
      "Sales": 65.36
  },
  {
      "County": "Caldwell",
      "Sales": 65.51
  },
  {
      "County": "Calloway",
      "Sales": 192.38
  },
  {
      "County": "Campbell",
      "Sales": 464.83
  },
  {
      "County": "Carlisle",
      "Sales": 25.55
  },
  {
      "County": "Carroll",
      "Sales": 55.96
  },
  {
      "County": "Carter",
      "Sales": 138.97
  },
  {
      "County": "Casey",
      "Sales": 82.08
  },
  {
      "County": "Christian",
      "Sales": 378.90
  },
  {
      "County": "Clark",
      "Sales": 181.94
  },
  {
      "County": "Clay",
      "Sales": 109.14
  },
  {
      "County": "Clinton",
      "Sales": 51.83
  },
  {
      "County": "Crittenden",
      "Sales": 47.28
  },
  {
      "County": "Cumberland",
      "Sales": 34.68
  },
  {
      "County": "Daviess",
      "Sales": 501.77
  },
  {
      "County": "Edmonson",
      "Sales": 61.62
  },
  {
      "County": "Elliott",
      "Sales": 39.02
  },
  {
      "County": "Estill",
      "Sales": 74.02
  },
  {
      "County": "Fayette",
      "Sales": 1575.68
  },
  {
      "County": "Fleming",
      "Sales": 74.12
  },
  {
      "County": "Floyd",
      "Sales": 197.85
  },
  {
      "County": "Franklin",
      "Sales": 253.64
  },
  {
      "County": "Fulton",
      "Sales": 32.62
  },
  {
      "County": "Gallatin",
      "Sales": 43.29
  },
  {
      "County": "Garrard",
      "Sales": 86.41
  },
  {
      "County": "Grant",
      "Sales": 126.46
  },
  {
      "County": "Graves",
      "Sales": 191.33
  },
  {
      "County": "Grayson",
      "Sales": 132.81
  },
  {
      "County": "Green",
      "Sales": 57.12
  },
  {
      "County": "Greenup",
      "Sales": 186.57
  },
  {
      "County": "Hancock",
      "Sales": 44.38
  },
  {
      "County": "Hardin",
      "Sales": 552.72
  },
  {
      "County": "Harlan",
      "Sales": 145.59
  },
  {
      "County": "Harrison",
      "Sales": 94.60
  },
  {
      "County": "Hart",
      "Sales": 94.88
  },
  {
      "County": "Henderson",
      "Sales": 236.78
  },
  {
      "County": "Henry",
      "Sales": 78.90
  },
  {
      "County": "Hickman",
      "Sales": 24.24
  },
  {
      "County": "Hopkins",
      "Sales": 238.24
  },
  {
      "County": "Jackson",
      "Sales": 68.60
  },
  {
      "County": "Jefferson",
      "Sales": 3866.46
  },
  {
      "County": "Jessamine",
      "Sales": 256.32
  },
  {
      "County": "Johnson",
      "Sales": 119.80
  },
  {
      "County": "Kenton",
      "Sales": 833.47
  },
  {
      "County": "Knott",
      "Sales": 81.62
  },
  {
      "County": "Knox",
      "Sales": 162.41
  },
  {
      "County": "Larue",
      "Sales": 71.85
  },
  {
      "County": "Laurel",
      "Sales": 304.29
  },
  {
      "County": "Lawrence",
      "Sales": 81.00
  },
  {
      "County": "Lee",
      "Sales": 37.09
  },
  {
      "County": "Leslie",
      "Sales": 56.29
  },
  {
      "County": "Letcher",
      "Sales": 120.66
  },
  {
      "County": "Lewis",
      "Sales": 70.53
  },
  {
      "County": "Lincoln",
      "Sales": 124.50
  },
  {
      "County": "Livingston",
      "Sales": 47.81
  },
  {
      "County": "Logan",
      "Sales": 137.30
  },
  {
      "County": "Lyon",
      "Sales": 43.17
  },
  {
      "County": "McCracken",
      "Sales": 333.97
  },
  {
      "County": "McCreary",
      "Sales": 91.90
  },
  {
      "County": "McLean",
      "Sales": 48.51
  },
  {
      "County": "Madison",
      "Sales": 437.26
  },
  {
      "County": "Magoffin",
      "Sales": 66.16
  },
  {
      "County": "Marion",
      "Sales": 102.40
  },
  {
      "County": "Marshall",
      "Sales": 158.92
  },
  {
      "County": "Martin",
      "Sales": 64.61
  },
  {
      "County": "Mason",
      "Sales": 88.27
  },
  {
      "County": "Meade",
      "Sales": 149.23
  },
  {
      "County": "Menifee",
      "Sales": 32.12
  },
  {
      "County": "Mercer",
      "Sales": 109.07
  },
  {
      "County": "Metcalfe",
      "Sales": 51.00
  },
  {
      "County": "Monroe",
      "Sales": 54.57
  },
  {
      "County": "Montgomery",
      "Sales": 139.22
  },
  {
      "County": "Morgan",
      "Sales": 68.36
  },
  {
      "County": "Muhlenberg",
      "Sales": 159.29
  },
  {
      "County": "Nelson",
      "Sales": 227.54
  },
  {
      "County": "Nicholas",
      "Sales": 35.96
  },
  {
      "County": "Ohio",
      "Sales": 122.55
  },
  {
      "County": "Oldham",
      "Sales": 318.60
  },
  {
      "County": "Owen",
      "Sales": 54.47
  },
  {
      "County": "Owsley",
      "Sales": 23.78
  },
  {
      "County": "Pendleton",
      "Sales": 74.43
  },
  {
      "County": "Perry",
      "Sales": 143.10
  },
  {
      "County": "Pike",
      "Sales": 323.79
  },
  {
      "County": "Powell",
      "Sales": 63.83
  },
  {
      "County": "Pulaski",
      "Sales": 326.48
  },
  {
      "County": "Robertson",
      "Sales": 11.42
  },
  {
      "County": "Rockcastle",
      "Sales": 85.28
  },
  {
      "County": "Rowan",
      "Sales": 120.19
  },
  {
      "County": "Russell",
      "Sales": 90.69
  },
  {
      "County": "Scott",
      "Sales": 255.17
  },
  {
      "County": "Shelby",
      "Sales": 225.89
  },
  {
      "County": "Simpson",
      "Sales": 90.90
  },
  {
      "County": "Spencer",
      "Sales": 90.10
  },
  {
      "County": "Taylor",
      "Sales": 125.93
  },
  {
      "County": "Todd",
      "Sales": 63.87
  },
  {
      "County": "Trigg",
      "Sales": 73.02
  },
  {
      "County": "Trimble",
      "Sales": 45.04
  },
  {
      "County": "Union",
      "Sales": 76.78
  },
  {
      "County": "Warren",
      "Sales": 604.72
  },
  {
      "County": "Washington",
      "Sales": 60.67
  },
  {
      "County": "Wayne",
      "Sales": 105.64
  },
  {
      "County": "Webster",
      "Sales": 68.72
  },
  {
      "County": "Whitley",
      "Sales": 182.72
  },
  {
      "County": "Wolfe",
      "Sales": 37.03
  },
  {
      "County": "Woodford",
      "Sales": 129.12
  }
        ];
    }

    if (state == 'LA') {
        items = [
  {
    "County":"Acadia",
    "Sales":630.93
  },
  {
    "County":"Allen",
    "Sales":259.02
  },
  {
    "County":"Ascension",
    "Sales":1160.28
  },
  {
    "County":"Assumption",
    "Sales":235.18
  },
  {
    "County":"Avoyelles",
    "Sales":418.89
  },
  {
    "County":"Beauregard",
    "Sales":366.84
  },
  {
    "County":"Bienville",
    "Sales":141.81
  },
  {
    "County":"Bossier",
    "Sales":1255.93
  },
  {
    "County":"Caddo",
    "Sales":2585.30
  },
  {
    "County":"Calcasieu",
    "Sales":1980.87
  },
  {
    "County":"Caldwell",
    "Sales":101.32
  },
  {
    "County":"Cameron",
    "Sales":68.40
  },
  {
    "County":"Catahoula",
    "Sales":103.84
  },
  {
    "County":"Claiborne",
    "Sales":168.88
  },
  {
    "County":"Concordia",
    "Sales":207.34
  },
  {
    "County":"De Soto",
    "Sales":274.70
  },
  {
    "County":"East Baton Rouge",
    "Sales":4515.91
  },
  {
    "County":"East Carroll",
    "Sales":76.37
  },
  {
    "County":"East Feliciana",
    "Sales":200.10
  },
  {
    "County":"Evangeline",
    "Sales":340.58
  },
  {
    "County":"Franklin",
    "Sales":208.65
  },
  {
    "County":"Grant",
    "Sales":223.45
  },
  {
    "County":"Iberia",
    "Sales":749.34
  },
  {
    "County":"Iberville",
    "Sales":338.44
  },
  {
    "County":"Jackson",
    "Sales":163.42
  },
  {
    "County":"Jefferson",
    "Sales":4409.81
  },
  {
    "County":"Jefferson Davis",
    "Sales":317.48
  },
  {
    "County":"Lafayette",
    "Sales":2341.45
  },
  {
    "County":"Lafourche",
    "Sales":985.30
  },
  {
    "County":"La Salle",
    "Sales":149.88
  },
  {
    "County":"Lincoln",
    "Sales":480.92
  },
  {
    "County":"Livingston",
    "Sales":1359.69
  },
  {
    "County":"Madison",
    "Sales":120.97
  },
  {
    "County":"Morehouse",
    "Sales":274.44
  },
  {
    "County":"Natchitoches",
    "Sales":396.97
  },
  {
    "County":"Orleans",
    "Sales":3841.28
  },
  {
    "County":"Ouachita",
    "Sales":1584.53
  },
  {
    "County":"Plaquemines",
    "Sales":238.87
  },
  {
    "County":"Pointe Coupee",
    "Sales":228.21
  },
  {
    "County":"Rapides",
    "Sales":1346.20
  },
  {
    "County":"Red River",
    "Sales":90.21
  },
  {
    "County":"Richland",
    "Sales":211.55
  },
  {
    "County":"Sabine",
    "Sales":245.81
  },
  {
    "County":"St. Bernard",
    "Sales":441.04
  },
  {
    "County":"St. Charles",
    "Sales":533.69
  },
  {
    "County":"St. Helena",
    "Sales":110.30
  },
  {
    "County":"St. James",
    "Sales":220.63
  },
  {
    "County":"St. John the Baptist",
    "Sales":443.87
  },
  {
    "County":"St. Landry",
    "Sales":846.47
  },
  {
    "County":"St. Martin",
    "Sales":536.93
  },
  {
    "County":"St. Mary",
    "Sales":543.08
  },
  {
    "County":"St. Tammany",
    "Sales":2457.97
  },
  {
    "County":"Tangipahoa",
    "Sales":1272.05
  },
  {
    "County":"Tensas",
    "Sales":49.78
  },
  {
    "County":"Terrebonne",
    "Sales":1143.61
  },
  {
    "County":"Union",
    "Sales":226.63
  },
  {
    "County":"Vermilion",
    "Sales":601.00
  },
  {
    "County":"Vernon",
    "Sales":533.58
  },
  {
    "County":"Washington",
    "Sales":470.82
  },
  {
    "County":"Webster",
    "Sales":412.59
  },
  {
    "County":"West Baton Rouge",
    "Sales":249.24
  },
  {
    "County":"West Carroll",
    "Sales":116.29
  },
  {
    "County":"West Feliciana",
    "Sales":156.65
  },
  {
    "County":"Winn",
    "Sales":150.25
  }
		];
    }

    if (state == 'MA') {
        items = [
  {
      "County": "Barnstable",
      "Sales": 1511.15
  },
  {
      "County": "Berkshire",
      "Sales": 910.85
  },
  {
      "County": "Bristol",
      "Sales": 3885.46
  },
  {
      "County": "Dukes",
      "Sales": 121.29
  },
  {
      "County": "Essex",
      "Sales": 5359.93
  },
  {
      "County": "Franklin",
      "Sales": 500.61
  },
  {
      "County": "Hampden",
      "Sales": 3284.76
  },
  {
      "County": "Hampshire",
      "Sales": 1121.79
  },
  {
      "County": "Middlesex",
      "Sales": 10914.57
  },
  {
      "County": "Nantucket",
      "Sales": 73.09
  },
  {
      "County": "Norfolk",
      "Sales": 4834.53
  },
  {
      "County": "Plymouth",
      "Sales": 3527.94
  },
  {
      "County": "Suffolk",
      "Sales": 5310.39
  },
  {
      "County": "Worcester",
      "Sales": 5687.17
  }
        ];
    }

    if (state == 'MD') {
        items = [
  {
      "County": "Allegany",
      "Sales": 504.79
  },
  {
      "County": "Anne Arundel",
      "Sales": 3815.70
  },
  {
      "County": "Baltimore",
      "Sales": 5650.78
  },
  {
      "County": "Calvert",
      "Sales": 621.26
  },
  {
      "County": "Caroline",
      "Sales": 224.47
  },
  {
      "County": "Carroll",
      "Sales": 1150.49
  },
  {
      "County": "Cecil",
      "Sales": 699.73
  },
  {
      "County": "Charles",
      "Sales": 1049.56
  },
  {
      "County": "Dorchester",
      "Sales": 224.24
  },
  {
      "County": "Frederick",
      "Sales": 1657.50
  },
  {
      "County": "Garrett",
      "Sales": 205.22
  },
  {
      "County": "Harford",
      "Sales": 1711.10
  },
  {
      "County": "Howard",
      "Sales": 2091.23
  },
  {
      "County": "Kent",
      "Sales": 136.93
  },
  {
      "County": "Montgomery",
      "Sales": 6980.45
  },
  {
      "County": "Prince George's",
      "Sales": 6111.25
  },
  {
      "County": "Queen Anne's",
      "Sales": 333.12
  },
  {
      "County": "St. Mary's",
      "Sales": 752.73
  },
  {
      "County": "Somerset",
      "Sales": 180.39
  },
  {
      "County": "Talbot",
      "Sales": 260.43
  },
  {
      "County": "Washington",
      "Sales": 1027.06
  },
  {
      "County": "Wicomico",
      "Sales": 692.75
  },
  {
      "County": "Worcester",
      "Sales": 354.42
  },
  {
      "County": "Baltimore City",
      "Sales": 4271.33
  }
        ];
    }

    if (state == 'ME') {
        items = [
  {
      "County": "Androscoggin",
      "Sales": 754.62
  },
  {
      "County": "Aroostook",
      "Sales": 491.29
  },
  {
      "County": "Cumberland",
      "Sales": 2001.89
  },
  {
      "County": "Franklin",
      "Sales": 213.86
  },
  {
      "County": "Hancock",
      "Sales": 384.63
  },
  {
      "County": "Kennebec",
      "Sales": 849.72
  },
  {
      "County": "Knox",
      "Sales": 277.36
  },
  {
      "County": "Lincoln",
      "Sales": 239.06
  },
  {
      "County": "Oxford",
      "Sales": 401.68
  },
  {
      "County": "Penobscot",
      "Sales": 1075.53
  },
  {
      "County": "Piscataquis",
      "Sales": 120.09
  },
  {
      "County": "Sagadahoc",
      "Sales": 245.54
  },
  {
      "County": "Somerset",
      "Sales": 362.61
  },
  {
      "County": "Waldo",
      "Sales": 273.08
  },
  {
      "County": "Washington",
      "Sales": 225.75
  },
  {
      "County": "York",
      "Sales": 1398.60
  }
        ];
    }

    if (state == 'MI') {
        items = [
  {
      "County": "Alcona",
      "Sales": 89.20
  },
  {
      "County": "Alger",
      "Sales": 80.30
  },
  {
      "County": "Allegan",
      "Sales": 948.95
  },
  {
      "County": "Alpena",
      "Sales": 245.32
  },
  {
      "County": "Antrim",
      "Sales": 197.07
  },
  {
      "County": "Arenac",
      "Sales": 130.60
  },
  {
      "County": "Baraga",
      "Sales": 73.32
  },
  {
      "County": "Barry",
      "Sales": 498.35
  },
  {
      "County": "Bay",
      "Sales": 900.89
  },
  {
      "County": "Benzie",
      "Sales": 146.97
  },
  {
      "County": "Berrien",
      "Sales": 1309.21
  },
  {
      "County": "Branch",
      "Sales": 368.08
  },
  {
      "County": "Calhoun",
      "Sales": 1138.53
  },
  {
      "County": "Cass",
      "Sales": 437.75
  },
  {
      "County": "Charlevoix",
      "Sales": 220.34
  },
  {
      "County": "Cheboygan",
      "Sales": 216.94
  },
  {
      "County": "Chippewa",
      "Sales": 326.32
  },
  {
      "County": "Clare",
      "Sales": 257.78
  },
  {
      "County": "Clinton",
      "Sales": 647.12
  },
  {
      "County": "Crawford",
      "Sales": 117.25
  },
  {
      "County": "Delta",
      "Sales": 311.21
  },
  {
      "County": "Dickinson",
      "Sales": 220.08
  },
  {
      "County": "Eaton",
      "Sales": 913.68
  },
  {
      "County": "Emmet",
      "Sales": 279.46
  },
  {
      "County": "Genesee",
      "Sales": 3502.78
  },
  {
      "County": "Gladwin",
      "Sales": 214.98
  },
  {
      "County": "Gogebic",
      "Sales": 134.22
  },
  {
      "County": "Grand Traverse",
      "Sales": 758.84
  },
  {
      "County": "Gratiot",
      "Sales": 353.91
  },
  {
      "County": "Hillsdale",
      "Sales": 388.76
  },
  {
      "County": "Houghton",
      "Sales": 305.48
  },
  {
      "County": "Huron",
      "Sales": 271.74
  },
  {
      "County": "Ingham",
      "Sales": 2380.02
  },
  {
      "County": "Ionia",
      "Sales": 540.31
  },
  {
      "County": "Iosco",
      "Sales": 214.44
  },
  {
      "County": "Iron",
      "Sales": 97.11
  },
  {
      "County": "Isabella",
      "Sales": 593.97
  },
  {
      "County": "Jackson",
      "Sales": 1352.36
  },
  {
      "County": "Kalamazoo",
      "Sales": 2164.91
  },
  {
      "County": "Kalkaska",
      "Sales": 145.01
  },
  {
      "County": "Kent",
      "Sales": 5242.67
  },
  {
      "County": "Keweenaw",
      "Sales": 18.48
  },
  {
      "County": "Lake",
      "Sales": 96.02
  },
  {
      "County": "Lapeer",
      "Sales": 745.37
  },
  {
      "County": "Leelanau",
      "Sales": 183.39
  },
  {
      "County": "Lenawee",
      "Sales": 836.43
  },
  {
      "County": "Livingston",
      "Sales": 1555.37
  },
  {
      "County": "Luce",
      "Sales": 54.83
  },
  {
      "County": "Mackinac",
      "Sales": 93.28
  },
  {
      "County": "Macomb",
      "Sales": 7208.09
  },
  {
      "County": "Manistee",
      "Sales": 206.18
  },
  {
      "County": "Marquette",
      "Sales": 570.90
  },
  {
      "County": "Mason",
      "Sales": 241.22
  },
  {
      "County": "Mecosta",
      "Sales": 363.52
  },
  {
      "County": "Menominee",
      "Sales": 200.62
  },
  {
      "County": "Midland",
      "Sales": 707.67
  },
  {
      "County": "Missaukee",
      "Sales": 126.92
  },
  {
      "County": "Monroe",
      "Sales": 1268.09
  },
  {
      "County": "Montcalm",
      "Sales": 532.15
  },
  {
      "County": "Montmorency",
      "Sales": 78.85
  },
  {
      "County": "Muskegon",
      "Sales": 1442.08
  },
  {
      "County": "Newaygo",
      "Sales": 404.78
  },
  {
      "County": "Oakland",
      "Sales": 10386.17
  },
  {
      "County": "Oceana",
      "Sales": 221.32
  },
  {
      "County": "Ogemaw",
      "Sales": 179.06
  },
  {
      "County": "Ontonagon",
      "Sales": 53.31
  },
  {
      "County": "Osceola",
      "Sales": 196.14
  },
  {
      "County": "Oscoda",
      "Sales": 70.66
  },
  {
      "County": "Otsego",
      "Sales": 203.47
  },
  {
      "County": "Ottawa",
      "Sales": 2299.63
  },
  {
      "County": "Presque Isle",
      "Sales": 110.15
  },
  {
      "County": "Roscommon",
      "Sales": 202.51
  },
  {
      "County": "Saginaw",
      "Sales": 1657.40
  },
  {
      "County": "St. Clair",
      "Sales": 1353.20
  },
  {
      "County": "St. Joseph",
      "Sales": 514.10
  },
  {
      "County": "Sanilac",
      "Sales": 352.68
  },
  {
      "County": "Schoolcraft",
      "Sales": 69.55
  },
  {
      "County": "Shiawassee",
      "Sales": 581.02
  },
  {
      "County": "Tuscola",
      "Sales": 457.59
  },
  {
      "County": "Van Buren",
      "Sales": 636.30
  },
  {
      "County": "Washtenaw",
      "Sales": 2987.23
  },
  {
      "County": "Wayne",
      "Sales": 14970.52
  },
  {
      "County": "Wexford",
      "Sales": 275.29
  }
        ];
    }

    if (state == 'MN') {
        items = [
  {
      "County": "Aitkin",
      "Sales": 88.83
  },
  {
      "County": "Anoka",
      "Sales": 1915.88
  },
  {
      "County": "Becker",
      "Sales": 187.51
  },
  {
      "County": "Beltrami",
      "Sales": 257.70
  },
  {
      "County": "Benton",
      "Sales": 221.27
  },
  {
      "County": "Big Stone",
      "Sales": 28.90
  },
  {
      "County": "Blue Earth",
      "Sales": 369.75
  },
  {
      "County": "Brown",
      "Sales": 142.94
  },
  {
      "County": "Carlton",
      "Sales": 200.09
  },
  {
      "County": "Carver",
      "Sales": 539.23
  },
  {
      "County": "Cass",
      "Sales": 161.13
  },
  {
      "County": "Chippewa",
      "Sales": 68.24
  },
  {
      "County": "Chisago",
      "Sales": 303.36
  },
  {
      "County": "Clay",
      "Sales": 342.29
  },
  {
      "County": "Clearwater",
      "Sales": 49.87
  },
  {
      "County": "Cook",
      "Sales": 29.34
  },
  {
      "County": "Cottonwood",
      "Sales": 65.55
  },
  {
      "County": "Crow Wing",
      "Sales": 356.66
  },
  {
      "County": "Dakota",
      "Sales": 2305.09
  },
  {
      "County": "Dodge",
      "Sales": 114.82
  },
  {
      "County": "Douglas",
      "Sales": 206.21
  },
  {
      "County": "Faribault",
      "Sales": 80.08
  },
  {
      "County": "Fillmore",
      "Sales": 117.57
  },
  {
      "County": "Freeborn",
      "Sales": 174.63
  },
  {
      "County": "Goodhue",
      "Sales": 262.18
  },
  {
      "County": "Grant",
      "Sales": 33.79
  },
  {
      "County": "Hennepin",
      "Sales": 6764.32
  },
  {
      "County": "Houston",
      "Sales": 106.08
  },
  {
      "County": "Hubbard",
      "Sales": 116.57
  },
  {
      "County": "Isanti",
      "Sales": 215.57
  },
  {
      "County": "Itasca",
      "Sales": 257.10
  },
  {
      "County": "Jackson",
      "Sales": 57.89
  },
  {
      "County": "Kanabec",
      "Sales": 90.26
  },
  {
      "County": "Kandiyohi",
      "Sales": 239.31
  },
  {
      "County": "Kittson",
      "Sales": 25.41
  },
  {
      "County": "Koochiching",
      "Sales": 74.52
  },
  {
      "County": "Lac qui Parle",
      "Sales": 39.65
  },
  {
      "County": "Lake",
      "Sales": 60.81
  },
  {
      "County": "Lake of the Woods",
      "Sales": 22.17
  },
  {
      "County": "Le Sueur",
      "Sales": 156.92
  },
  {
      "County": "Lincoln",
      "Sales": 32.90
  },
  {
      "County": "Lyon",
      "Sales": 143.81
  },
  {
      "County": "McLeod",
      "Sales": 202.67
  },
  {
      "County": "Mahnomen",
      "Sales": 31.22
  },
  {
      "County": "Marshall",
      "Sales": 53.18
  },
  {
      "County": "Martin",
      "Sales": 115.23
  },
  {
      "County": "Meeker",
      "Sales": 130.45
  },
  {
      "County": "Mille Lacs",
      "Sales": 145.77
  },
  {
      "County": "Morrison",
      "Sales": 185.49
  },
  {
      "County": "Mower",
      "Sales": 221.91
  },
  {
      "County": "Murray",
      "Sales": 48.15
  },
  {
      "County": "Nicollet",
      "Sales": 186.39
  },
  {
      "County": "Nobles",
      "Sales": 121.98
  },
  {
      "County": "Norman",
      "Sales": 37.42
  },
  {
      "County": "Olmsted",
      "Sales": 842.03
  },
  {
      "County": "Otter Tail",
      "Sales": 324.91
  },
  {
      "County": "Pennington",
      "Sales": 79.66
  },
  {
      "County": "Pine",
      "Sales": 164.22
  },
  {
      "County": "Pipestone",
      "Sales": 52.31
  },
  {
      "County": "Polk",
      "Sales": 178.13
  },
  {
      "County": "Pope",
      "Sales": 61.69
  },
  {
      "County": "Ramsey",
      "Sales": 2972.08
  },
  {
      "County": "Red Lake",
      "Sales": 22.89
  },
  {
      "County": "Redwood",
      "Sales": 88.84
  },
  {
      "County": "Renville",
      "Sales": 85.58
  },
  {
      "County": "Rice",
      "Sales": 367.05
  },
  {
      "County": "Rock",
      "Sales": 53.72
  },
  {
      "County": "Roseau",
      "Sales": 87.57
  },
  {
      "County": "St. Louis",
      "Sales": 1131.58
  },
  {
      "County": "Scott",
      "Sales": 774.36
  },
  {
      "County": "Sherburne",
      "Sales": 508.73
  },
  {
      "County": "Sibley",
      "Sales": 85.05
  },
  {
      "County": "Stearns",
      "Sales": 858.21
  },
  {
      "County": "Steele",
      "Sales": 205.76
  },
  {
      "County": "Stevens",
      "Sales": 54.93
  },
  {
      "County": "Swift",
      "Sales": 53.87
  },
  {
      "County": "Todd",
      "Sales": 137.58
  },
  {
      "County": "Traverse",
      "Sales": 19.44
  },
  {
      "County": "Wabasha",
      "Sales": 121.00
  },
  {
      "County": "Wadena",
      "Sales": 77.89
  },
  {
      "County": "Waseca",
      "Sales": 107.76
  },
  {
      "County": "Washington",
      "Sales": 1391.50
  },
  {
      "County": "Watonwan",
      "Sales": 62.84
  },
  {
      "County": "Wilkin",
      "Sales": 37.00
  },
  {
      "County": "Winona",
      "Sales": 289.09
  },
  {
      "County": "Wright",
      "Sales": 724.92
  },
  {
      "County": "Yellow Medicine",
      "Sales": 57.23
  }
        ];
    }

    if (state == 'MO') {
        items = [
  {
      "County": "Adair",
      "Sales": 270.85
  },
  {
      "County": "Andrew",
      "Sales": 184.77
  },
  {
      "County": "Atchison",
      "Sales": 57.71
  },
  {
      "County": "Audrain",
      "Sales": 271.79
  },
  {
      "County": "Barry",
      "Sales": 376.77
  },
  {
      "County": "Barton",
      "Sales": 130.01
  },
  {
      "County": "Bates",
      "Sales": 175.29
  },
  {
      "County": "Benton",
      "Sales": 200.52
  },
  {
      "County": "Bollinger",
      "Sales": 132.29
  },
  {
      "County": "Boone",
      "Sales": 1808.78
  },
  {
      "County": "Buchanan",
      "Sales": 949.35
  },
  {
      "County": "Butler",
      "Sales": 456.32
  },
  {
      "County": "Caldwell",
      "Sales": 96.35
  },
  {
      "County": "Callaway",
      "Sales": 469.84
  },
  {
      "County": "Camden",
      "Sales": 464.58
  },
  {
      "County": "Cape Girardeau",
      "Sales": 818.95
  },
  {
      "County": "Carroll",
      "Sales": 96.67
  },
  {
      "County": "Carter",
      "Sales": 66.63
  },
  {
      "County": "Cass",
      "Sales": 1065.96
  },
  {
      "County": "Cedar",
      "Sales": 147.36
  },
  {
      "County": "Chariton",
      "Sales": 80.79
  },
  {
      "County": "Christian",
      "Sales": 856.86
  },
  {
      "County": "Clark",
      "Sales": 73.19
  },
  {
      "County": "Clay",
      "Sales": 2441.11
  },
  {
      "County": "Clinton",
      "Sales": 217.88
  },
  {
      "County": "Cole",
      "Sales": 812.38
  },
  {
      "County": "Cooper",
      "Sales": 186.91
  },
  {
      "County": "Crawford",
      "Sales": 259.95
  },
  {
      "County": "Dade",
      "Sales": 80.26
  },
  {
      "County": "Dallas",
      "Sales": 175.13
  },
  {
      "County": "Daviess",
      "Sales": 87.85
  },
  {
      "County": "DeKalb",
      "Sales": 136.00
  },
  {
      "County": "Dent",
      "Sales": 166.61
  },
  {
      "County": "Douglas",
      "Sales": 143.15
  },
  {
      "County": "Dunklin",
      "Sales": 335.89
  },
  {
      "County": "Franklin",
      "Sales": 1078.41
  },
  {
      "County": "Gasconade",
      "Sales": 157.83
  },
  {
      "County": "Gentry",
      "Sales": 71.76
  },
  {
      "County": "Greene",
      "Sales": 3006.68
  },
  {
      "County": "Grundy",
      "Sales": 109.68
  },
  {
      "County": "Harrison",
      "Sales": 92.58
  },
  {
      "County": "Henry",
      "Sales": 233.64
  },
  {
      "County": "Hickory",
      "Sales": 98.56
  },
  {
      "County": "Holt",
      "Sales": 48.38
  },
  {
      "County": "Howard",
      "Sales": 108.64
  },
  {
      "County": "Howell",
      "Sales": 427.83
  },
  {
      "County": "Iron",
      "Sales": 109.56
  },
  {
      "County": "Jackson",
      "Sales": 7202.35
  },
  {
      "County": "Jasper",
      "Sales": 1232.86
  },
  {
      "County": "Jefferson",
      "Sales": 2344.97
  },
  {
      "County": "Johnson",
      "Sales": 578.01
  },
  {
      "County": "Knox",
      "Sales": 43.08
  },
  {
      "County": "Laclede",
      "Sales": 377.78
  },
  {
      "County": "Lafayette",
      "Sales": 348.92
  },
  {
      "County": "Lawrence",
      "Sales": 404.45
  },
  {
      "County": "Lewis",
      "Sales": 107.53
  },
  {
      "County": "Lincoln",
      "Sales": 570.47
  },
  {
      "County": "Linn",
      "Sales": 131.00
  },
  {
      "County": "Livingston",
      "Sales": 157.51
  },
  {
      "County": "McDonald",
      "Sales": 238.93
  },
  {
      "County": "Macon",
      "Sales": 164.64
  },
  {
      "County": "Madison",
      "Sales": 131.67
  },
  {
      "County": "Maries",
      "Sales": 95.52
  },
  {
      "County": "Marion",
      "Sales": 306.14
  },
  {
      "County": "Mercer",
      "Sales": 39.14
  },
  {
      "County": "Miller",
      "Sales": 265.77
  },
  {
      "County": "Mississippi",
      "Sales": 151.27
  },
  {
      "County": "Moniteau",
      "Sales": 166.80
  },
  {
      "County": "Monroe",
      "Sales": 92.93
  },
  {
      "County": "Montgomery",
      "Sales": 126.73
  },
  {
      "County": "Morgan",
      "Sales": 214.64
  },
  {
      "County": "New Madrid",
      "Sales": 194.52
  },
  {
      "County": "Newton",
      "Sales": 623.27
  },
  {
      "County": "Nodaway",
      "Sales": 246.37
  },
  {
      "County": "Oregon",
      "Sales": 116.47
  },
  {
      "County": "Osage",
      "Sales": 144.98
  },
  {
      "County": "Ozark",
      "Sales": 101.26
  },
  {
      "County": "Pemiscot",
      "Sales": 188.78
  },
  {
      "County": "Perry",
      "Sales": 202.01
  },
  {
      "County": "Pettis",
      "Sales": 447.02
  },
  {
      "County": "Phelps",
      "Sales": 474.58
  },
  {
      "County": "Pike",
      "Sales": 197.74
  },
  {
      "County": "Platte",
      "Sales": 988.32
  },
  {
      "County": "Polk",
      "Sales": 328.07
  },
  {
      "County": "Pulaski",
      "Sales": 569.29
  },
  {
      "County": "Putnam",
      "Sales": 51.63
  },
  {
      "County": "Ralls",
      "Sales": 107.95
  },
  {
      "County": "Randolph",
      "Sales": 264.16
  },
  {
      "County": "Ray",
      "Sales": 244.02
  },
  {
      "County": "Reynolds",
      "Sales": 69.89
  },
  {
      "County": "Ripley",
      "Sales": 148.62
  },
  {
      "County": "St. Charles",
      "Sales": 3955.96
  },
  {
      "County": "St. Clair",
      "Sales": 100.48
  },
  {
      "County": "Ste. Genevieve",
      "Sales": 188.30
  },
  {
      "County": "St. Francois",
      "Sales": 701.33
  },
  {
      "County": "St. Louis",
      "Sales": 10607.04
  },
  {
      "County": "Saline",
      "Sales": 246.28
  },
  {
      "County": "Schuyler",
      "Sales": 46.16
  },
  {
      "County": "Scotland",
      "Sales": 52.12
  },
  {
      "County": "Scott",
      "Sales": 416.15
  },
  {
      "County": "Shannon",
      "Sales": 87.88
  },
  {
      "County": "Shelby",
      "Sales": 65.43
  },
  {
      "County": "Stoddard",
      "Sales": 315.42
  },
  {
      "County": "Stone",
      "Sales": 331.49
  },
  {
      "County": "Sullivan",
      "Sales": 68.30
  },
  {
      "County": "Taney",
      "Sales": 567.45
  },
  {
      "County": "Texas",
      "Sales": 271.53
  },
  {
      "County": "Vernon",
      "Sales": 221.89
  },
  {
      "County": "Warren",
      "Sales": 349.52
  },
  {
      "County": "Washington",
      "Sales": 266.62
  },
  {
      "County": "Wayne",
      "Sales": 141.97
  },
  {
      "County": "Webster",
      "Sales": 386.24
  },
  {
      "County": "Worth",
      "Sales": 22.14
  },
  {
      "County": "Wright",
      "Sales": 195.66
  },
  {
      "County": "St. Louis city",
      "Sales": 3372.58
  }
        ];
    }

    if (state == 'MS') {
        items = [
  {
      "County": "Adams",
      "Sales": 224.01
  },
  {
      "County": "Alcorn",
      "Sales": 260.49
  },
  {
      "County": "Amite",
      "Sales": 90.05
  },
  {
      "County": "Attala",
      "Sales": 134.85
  },
  {
      "County": "Benton",
      "Sales": 59.83
  },
  {
      "County": "Bolivar",
      "Sales": 237.69
  },
  {
      "County": "Calhoun",
      "Sales": 102.85
  },
  {
      "County": "Carroll",
      "Sales": 72.50
  },
  {
      "County": "Chickasaw",
      "Sales": 120.84
  },
  {
      "County": "Choctaw",
      "Sales": 58.44
  },
  {
      "County": "Claiborne",
      "Sales": 64.59
  },
  {
      "County": "Clarke",
      "Sales": 114.59
  },
  {
      "County": "Clay",
      "Sales": 142.46
  },
  {
      "County": "Coahoma",
      "Sales": 175.79
  },
  {
      "County": "Copiah",
      "Sales": 201.89
  },
  {
      "County": "Covington",
      "Sales": 135.59
  },
  {
      "County": "DeSoto",
      "Sales": 1174.45
  },
  {
      "County": "Forrest",
      "Sales": 537.93
  },
  {
      "County": "Franklin",
      "Sales": 55.36
  },
  {
      "County": "George",
      "Sales": 161.67
  },
  {
      "County": "Greene",
      "Sales": 99.43
  },
  {
      "County": "Grenada",
      "Sales": 150.87
  },
  {
      "County": "Hancock",
      "Sales": 318.09
  },
  {
      "County": "Harrison",
      "Sales": 1371.72
  },
  {
      "County": "Hinds",
      "Sales": 1709.59
  },
  {
      "County": "Holmes",
      "Sales": 128.64
  },
  {
      "County": "Humphreys",
      "Sales": 62.28
  },
  {
      "County": "Issaquena",
      "Sales": 9.74
  },
  {
      "County": "Itawamba",
      "Sales": 163.59
  },
  {
      "County": "Jackson",
      "Sales": 980.45
  },
  {
      "County": "Jasper",
      "Sales": 114.97
  },
  {
      "County": "Jefferson",
      "Sales": 53.26
  },
  {
      "County": "Jefferson Davis",
      "Sales": 83.41
  },
  {
      "County": "Jones",
      "Sales": 481.40
  },
  {
      "County": "Kemper",
      "Sales": 71.86
  },
  {
      "County": "Lafayette",
      "Sales": 358.24
  },
  {
      "County": "Lamar",
      "Sales": 410.48
  },
  {
      "County": "Lauderdale",
      "Sales": 560.24
  },
  {
      "County": "Lawrence",
      "Sales": 87.36
  },
  {
      "County": "Leake",
      "Sales": 163.27
  },
  {
      "County": "Lee",
      "Sales": 595.74
  },
  {
      "County": "Leflore",
      "Sales": 220.64
  },
  {
      "County": "Lincoln",
      "Sales": 243.00
  },
  {
      "County": "Lowndes",
      "Sales": 418.30
  },
  {
      "County": "Madison",
      "Sales": 700.95
  },
  {
      "County": "Marion",
      "Sales": 182.76
  },
  {
      "County": "Marshall",
      "Sales": 254.90
  },
  {
      "County": "Monroe",
      "Sales": 252.12
  },
  {
      "County": "Montgomery",
      "Sales": 73.67
  },
  {
      "County": "Neshoba",
      "Sales": 205.98
  },
  {
      "County": "Newton",
      "Sales": 151.41
  },
  {
      "County": "Noxubee",
      "Sales": 77.41
  },
  {
      "County": "Oktibbeha",
      "Sales": 342.36
  },
  {
      "County": "Panola",
      "Sales": 240.15
  },
  {
      "County": "Pearl River",
      "Sales": 384.45
  },
  {
      "County": "Perry",
      "Sales": 84.68
  },
  {
      "County": "Pike",
      "Sales": 279.33
  },
  {
      "County": "Pontotoc",
      "Sales": 215.69
  },
  {
      "County": "Prentiss",
      "Sales": 177.23
  },
  {
      "County": "Quitman",
      "Sales": 54.47
  },
  {
      "County": "Rankin",
      "Sales": 1024.55
  },
  {
      "County": "Scott",
      "Sales": 197.37
  },
  {
      "County": "Sharkey",
      "Sales": 32.87
  },
  {
      "County": "Simpson",
      "Sales": 191.97
  },
  {
      "County": "Smith",
      "Sales": 113.26
  },
  {
      "County": "Stone",
      "Sales": 125.77
  },
  {
      "County": "Sunflower",
      "Sales": 195.44
  },
  {
      "County": "Tallahatchie",
      "Sales": 105.28
  },
  {
      "County": "Tate",
      "Sales": 198.07
  },
  {
      "County": "Tippah",
      "Sales": 154.16
  },
  {
      "County": "Tishomingo",
      "Sales": 136.33
  },
  {
      "County": "Tunica",
      "Sales": 73.72
  },
  {
      "County": "Union",
      "Sales": 193.74
  },
  {
      "County": "Walthall",
      "Sales": 104.23
  },
  {
      "County": "Warren",
      "Sales": 336.60
  },
  {
      "County": "Washington",
      "Sales": 346.86
  },
  {
      "County": "Wayne",
      "Sales": 143.38
  },
  {
      "County": "Webster",
      "Sales": 69.72
  },
  {
      "County": "Wilkinson",
      "Sales": 65.49
  },
  {
      "County": "Winston",
      "Sales": 131.15
  },
  {
      "County": "Yalobusha",
      "Sales": 86.37
  },
  {
      "County": "Yazoo",
      "Sales": 194.65
  }
        ];
    }

    if (state == 'MT') {
        items = [
  {
      "County": "Beaverhead",
      "Sales": 19.79
  },
  {
      "County": "Big Horn",
      "Sales": 27.63
  },
  {
      "County": "Blaine",
      "Sales": 13.99
  },
  {
      "County": "Broadwater",
      "Sales": 12.06
  },
  {
      "County": "Carbon",
      "Sales": 21.90
  },
  {
      "County": "Carter",
      "Sales": 2.49
  },
  {
      "County": "Cascade",
      "Sales": 174.51
  },
  {
      "County": "Chouteau",
      "Sales": 12.39
  },
  {
      "County": "Custer",
      "Sales": 25.32
  },
  {
      "County": "Daniels",
      "Sales": 3.79
  },
  {
      "County": "Dawson",
      "Sales": 20.01
  },
  {
      "County": "Deer Lodge",
      "Sales": 19.76
  },
  {
      "County": "Fallon",
      "Sales": 6.52
  },
  {
      "County": "Fergus",
      "Sales": 24.36
  },
  {
      "County": "Flathead",
      "Sales": 197.15
  },
  {
      "County": "Gallatin",
      "Sales": 200.65
  },
  {
      "County": "Garfield",
      "Sales": 2.73
  },
  {
      "County": "Glacier",
      "Sales": 29.10
  },
  {
      "County": "Golden Valley",
      "Sales": 1.82
  },
  {
      "County": "Granite",
      "Sales": 6.65
  },
  {
      "County": "Hill",
      "Sales": 35.10
  },
  {
      "County": "Jefferson",
      "Sales": 24.39
  },
  {
      "County": "Judith Basin",
      "Sales": 4.27
  },
  {
      "County": "Lake",
      "Sales": 61.47
  },
  {
      "County": "Lewis and Clark",
      "Sales": 138.41
  },
  {
      "County": "Liberty",
      "Sales": 5.02
  },
  {
      "County": "Lincoln",
      "Sales": 41.22
  },
  {
      "County": "McCone",
      "Sales": 3.62
  },
  {
      "County": "Madison",
      "Sales": 16.34
  },
  {
      "County": "Meagher",
      "Sales": 4.10
  },
  {
      "County": "Mineral",
      "Sales": 9.06
  },
  {
      "County": "Missoula",
      "Sales": 236.84
  },
  {
      "County": "Musselshell",
      "Sales": 9.81
  },
  {
      "County": "Park",
      "Sales": 33.22
  },
  {
      "County": "Petroleum",
      "Sales": 1.07
  },
  {
      "County": "Phillips",
      "Sales": 8.85
  },
  {
      "County": "Pondera",
      "Sales": 13.16
  },
  {
      "County": "Powder River",
      "Sales": 3.70
  },
  {
      "County": "Powell",
      "Sales": 14.81
  },
  {
      "County": "Prairie",
      "Sales": 2.50
  },
  {
      "County": "Ravalli",
      "Sales": 86.48
  },
  {
      "County": "Richland",
      "Sales": 23.75
  },
  {
      "County": "Roosevelt",
      "Sales": 23.57
  },
  {
      "County": "Rosebud",
      "Sales": 19.76
  },
  {
      "County": "Sanders",
      "Sales": 24.07
  },
  {
      "County": "Sheridan",
      "Sales": 7.77
  },
  {
      "County": "Silver Bow",
      "Sales": 73.13
  },
  {
      "County": "Stillwater",
      "Sales": 19.74
  },
  {
      "County": "Sweet Grass",
      "Sales": 7.77
  },
  {
      "County": "Teton",
      "Sales": 12.85
  },
  {
      "County": "Toole",
      "Sales": 10.88
  },
  {
      "County": "Treasure",
      "Sales": 1.48
  },
  {
      "County": "Valley",
      "Sales": 16.16
  },
  {
      "County": "Wheatland",
      "Sales": 4.52
  },
  {
      "County": "Wibaux",
      "Sales": 2.37
  },
  {
      "County": "Yellowstone",
      "Sales": 326.56
  }
        ];
    }

    if (state == 'NC') {
        items = [
  {
      "County": "Alamance",
      "Sales": 899.94
  },
  {
      "County": "Alexander",
      "Sales": 215.28
  },
  {
      "County": "Alleghany",
      "Sales": 63.77
  },
  {
      "County": "Anson",
      "Sales": 152.50
  },
  {
      "County": "Ashe",
      "Sales": 158.28
  },
  {
      "County": "Avery",
      "Sales": 103.26
  },
  {
      "County": "Beaufort",
      "Sales": 276.69
  },
  {
      "County": "Bertie",
      "Sales": 118.59
  },
  {
      "County": "Bladen",
      "Sales": 203.12
  },
  {
      "County": "Brunswick",
      "Sales": 672.14
  },
  {
      "County": "Buncombe",
      "Sales": 1445.20
  },
  {
      "County": "Burke",
      "Sales": 523.73
  },
  {
      "County": "Cabarrus",
      "Sales": 1091.43
  },
  {
      "County": "Caldwell",
      "Sales": 477.96
  },
  {
      "County": "Camden",
      "Sales": 59.38
  },
  {
      "County": "Carteret",
      "Sales": 398.93
  },
  {
      "County": "Caswell",
      "Sales": 135.19
  },
  {
      "County": "Catawba",
      "Sales": 902.46
  },
  {
      "County": "Chatham",
      "Sales": 389.51
  },
  {
      "County": "Cherokee",
      "Sales": 158.67
  },
  {
      "County": "Chowan",
      "Sales": 85.84
  },
  {
      "County": "Clay",
      "Sales": 61.70
  },
  {
      "County": "Cleveland",
      "Sales": 565.73
  },
  {
      "County": "Columbus",
      "Sales": 333.71
  },
  {
      "County": "Craven",
      "Sales": 609.12
  },
  {
      "County": "Cumberland",
      "Sales": 1899.66
  },
  {
      "County": "Currituck",
      "Sales": 142.22
  },
  {
      "County": "Dare",
      "Sales": 204.14
  },
  {
      "County": "Davidson",
      "Sales": 952.65
  },
  {
      "County": "Davie",
      "Sales": 242.24
  },
  {
      "County": "Duplin",
      "Sales": 350.26
  },
  {
      "County": "Durham",
      "Sales": 1679.66
  },
  {
      "County": "Edgecombe",
      "Sales": 323.97
  },
  {
      "County": "Forsyth",
      "Sales": 2105.72
  },
  {
      "County": "Franklin",
      "Sales": 362.94
  },
  {
      "County": "Gaston",
      "Sales": 1220.81
  },
  {
      "County": "Gates",
      "Sales": 67.91
  },
  {
      "County": "Graham",
      "Sales": 50.93
  },
  {
      "County": "Granville",
      "Sales": 339.71
  },
  {
      "County": "Greene",
      "Sales": 123.77
  },
  {
      "County": "Guilford",
      "Sales": 2953.27
  },
  {
      "County": "Halifax",
      "Sales": 311.60
  },
  {
      "County": "Harnett",
      "Sales": 728.61
  },
  {
      "County": "Haywood",
      "Sales": 345.01
  },
  {
      "County": "Henderson",
      "Sales": 638.56
  },
  {
      "County": "Hertford",
      "Sales": 142.42
  },
  {
      "County": "Hoke",
      "Sales": 299.18
  },
  {
      "County": "Hyde",
      "Sales": 33.35
  },
  {
      "County": "Iredell",
      "Sales": 959.05
  },
  {
      "County": "Jackson",
      "Sales": 238.54
  },
  {
      "County": "Johnston",
      "Sales": 1037.45
  },
  {
      "County": "Jones",
      "Sales": 59.55
  },
  {
      "County": "Lee",
      "Sales": 351.32
  },
  {
      "County": "Lenoir",
      "Sales": 343.44
  },
  {
      "County": "Lincoln",
      "Sales": 464.84
  },
  {
      "County": "McDowell",
      "Sales": 262.10
  },
  {
      "County": "Macon",
      "Sales": 197.37
  },
  {
      "County": "Madison",
      "Sales": 122.55
  },
  {
      "County": "Martin",
      "Sales": 138.15
  },
  {
      "County": "Mecklenburg",
      "Sales": 5776.88
  },
  {
      "County": "Mitchell",
      "Sales": 89.35
  },
  {
      "County": "Montgomery",
      "Sales": 160.72
  },
  {
      "County": "Moore",
      "Sales": 533.90
  },
  {
      "County": "Nash",
      "Sales": 554.34
  },
  {
      "County": "New Hanover",
      "Sales": 1243.23
  },
  {
      "County": "Northampton",
      "Sales": 121.48
  },
  {
      "County": "Onslow",
      "Sales": 1079.74
  },
  {
      "County": "Orange",
      "Sales": 818.18
  },
  {
      "County": "Pamlico",
      "Sales": 75.51
  },
  {
      "County": "Pasquotank",
      "Sales": 233.07
  },
  {
      "County": "Pender",
      "Sales": 322.57
  },
  {
      "County": "Perquimans",
      "Sales": 79.29
  },
  {
      "County": "Person",
      "Sales": 228.96
  },
  {
      "County": "Pitt",
      "Sales": 1015.86
  },
  {
      "County": "Polk",
      "Sales": 118.99
  },
  {
      "County": "Randolph",
      "Sales": 831.15
  },
  {
      "County": "Richmond",
      "Sales": 270.52
  },
  {
      "County": "Robeson",
      "Sales": 786.05
  },
  {
      "County": "Rockingham",
      "Sales": 535.60
  },
  {
      "County": "Rowan",
      "Sales": 806.35
  },
  {
      "County": "Rutherford",
      "Sales": 390.32
  },
  {
      "County": "Sampson",
      "Sales": 373.96
  },
  {
      "County": "Scotland",
      "Sales": 210.01
  },
  {
      "County": "Stanly",
      "Sales": 353.47
  },
  {
      "County": "Stokes",
      "Sales": 271.58
  },
  {
      "County": "Surry",
      "Sales": 425.84
  },
  {
      "County": "Swain",
      "Sales": 81.95
  },
  {
      "County": "Transylvania",
      "Sales": 191.81
  },
  {
      "County": "Tyrrell",
      "Sales": 23.95
  },
  {
      "County": "Union",
      "Sales": 1240.26
  },
  {
      "County": "Vance",
      "Sales": 261.21
  },
  {
      "County": "Wake",
      "Sales": 5679.59
  },
  {
      "County": "Warren",
      "Sales": 119.94
  },
  {
      "County": "Washington",
      "Sales": 74.16
  },
  {
      "County": "Watauga",
      "Sales": 305.30
  },
  {
      "County": "Wayne",
      "Sales": 726.25
  },
  {
      "County": "Wilkes",
      "Sales": 402.37
  },
  {
      "County": "Wilson",
      "Sales": 476.08
  },
  {
      "County": "Yadkin",
      "Sales": 221.74
  },
  {
      "County": "Yancey",
      "Sales": 102.40
  }
        ];
    }

    if (state == 'ND') {
        items = [
  {
      "County": "Adams",
      "Sales": 9.78
  },
  {
      "County": "Barnes",
      "Sales": 46.38
  },
  {
      "County": "Benson",
      "Sales": 28.50
  },
  {
      "County": "Billings",
      "Sales": 3.62
  },
  {
      "County": "Bottineau",
      "Sales": 27.92
  },
  {
      "County": "Bowman",
      "Sales": 13.32
  },
  {
      "County": "Burke",
      "Sales": 9.56
  },
  {
      "County": "Burleigh",
      "Sales": 366.61
  },
  {
      "County": "Cass",
      "Sales": 674.85
  },
  {
      "County": "Cavalier",
      "Sales": 16.15
  },
  {
      "County": "Dickey",
      "Sales": 21.75
  },
  {
      "County": "Divide",
      "Sales": 9.59
  },
  {
      "County": "Dunn",
      "Sales": 17.25
  },
  {
      "County": "Eddy",
      "Sales": 9.96
  },
  {
      "County": "Emmons",
      "Sales": 14.45
  },
  {
      "County": "Foster",
      "Sales": 13.95
  },
  {
      "County": "Golden Valley",
      "Sales": 7.56
  },
  {
      "County": "Grand Forks",
      "Sales": 286.71
  },
  {
      "County": "Grant",
      "Sales": 9.85
  },
  {
      "County": "Griggs",
      "Sales": 9.52
  },
  {
      "County": "Hettinger",
      "Sales": 11.02
  },
  {
      "County": "Kidder",
      "Sales": 10.06
  },
  {
      "County": "LaMoure",
      "Sales": 17.27
  },
  {
      "County": "Logan",
      "Sales": 8.07
  },
  {
      "County": "McHenry",
      "Sales": 24.54
  },
  {
      "County": "McIntosh",
      "Sales": 11.41
  },
  {
      "County": "McKenzie",
      "Sales": 38.60
  },
  {
      "County": "McLean",
      "Sales": 39.44
  },
  {
      "County": "Mercer",
      "Sales": 35.61
  },
  {
      "County": "Morton",
      "Sales": 120.15
  },
  {
      "County": "Mountrail",
      "Sales": 38.86
  },
  {
      "County": "Nelson",
      "Sales": 12.83
  },
  {
      "County": "Oliver",
      "Sales": 7.77
  },
  {
      "County": "Pembina",
      "Sales": 29.76
  },
  {
      "County": "Pierce",
      "Sales": 18.45
  },
  {
      "County": "Ramsey",
      "Sales": 47.89
  },
  {
      "County": "Ransom",
      "Sales": 22.86
  },
  {
      "County": "Renville",
      "Sales": 10.81
  },
  {
      "County": "Richland",
      "Sales": 67.72
  },
  {
      "County": "Rolette",
      "Sales": 60.44
  },
  {
      "County": "Sargent",
      "Sales": 16.12
  },
  {
      "County": "Sheridan",
      "Sales": 5.40
  },
  {
      "County": "Sioux",
      "Sales": 18.36
  },
  {
      "County": "Slope",
      "Sales": 3.15
  },
  {
      "County": "Stark",
      "Sales": 116.93
  },
  {
      "County": "Steele",
      "Sales": 8.12
  },
  {
      "County": "Stutsman",
      "Sales": 87.53
  },
  {
      "County": "Towner",
      "Sales": 9.60
  },
  {
      "County": "Traill",
      "Sales": 34.17
  },
  {
      "County": "Walsh",
      "Sales": 46.02
  },
  {
      "County": "Ward",
      "Sales": 281.79
  },
  {
      "County": "Wells",
      "Sales": 17.43
  },
  {
      "County": "Williams",
      "Sales": 122.66
  }
        ];
    }

    if (state == 'NE') {
        items = [
  {
      "County": "Adams",
      "Sales": 151.06
  },
  {
      "County": "Antelope",
      "Sales": 30.85
  },
  {
      "County": "Arthur",
      "Sales": 2.19
  },
  {
      "County": "Banner",
      "Sales": 3.63
  },
  {
      "County": "Blaine",
      "Sales": 2.30
  },
  {
      "County": "Boone",
      "Sales": 25.75
  },
  {
      "County": "Box Butte",
      "Sales": 54.02
  },
  {
      "County": "Boyd",
      "Sales": 9.71
  },
  {
      "County": "Brown",
      "Sales": 13.98
  },
  {
      "County": "Buffalo",
      "Sales": 228.87
  },
  {
      "County": "Burt",
      "Sales": 31.42
  },
  {
      "County": "Butler",
      "Sales": 39.72
  },
  {
      "County": "Cass",
      "Sales": 121.18
  },
  {
      "County": "Cedar",
      "Sales": 41.63
  },
  {
      "County": "Chase",
      "Sales": 19.12
  },
  {
      "County": "Cherry",
      "Sales": 27.66
  },
  {
      "County": "Cheyenne",
      "Sales": 48.22
  },
  {
      "County": "Clay",
      "Sales": 30.55
  },
  {
      "County": "Colfax",
      "Sales": 49.82
  },
  {
      "County": "Cuming",
      "Sales": 42.99
  },
  {
      "County": "Custer",
      "Sales": 51.57
  },
  {
      "County": "Dakota",
      "Sales": 100.10
  },
  {
      "County": "Dawes",
      "Sales": 43.43
  },
  {
      "County": "Dawson",
      "Sales": 115.68
  },
  {
      "County": "Deuel",
      "Sales": 9.26
  },
  {
      "County": "Dixon",
      "Sales": 27.96
  },
  {
      "County": "Dodge",
      "Sales": 174.50
  },
  {
      "County": "Douglas",
      "Sales": 2567.46
  },
  {
      "County": "Dundy",
      "Sales": 9.47
  },
  {
      "County": "Fillmore",
      "Sales": 27.23
  },
  {
      "County": "Franklin",
      "Sales": 14.74
  },
  {
      "County": "Frontier",
      "Sales": 12.95
  },
  {
      "County": "Furnas",
      "Sales": 23.25
  },
  {
      "County": "Gage",
      "Sales": 104.48
  },
  {
      "County": "Garden",
      "Sales": 9.09
  },
  {
      "County": "Garfield",
      "Sales": 9.72
  },
  {
      "County": "Gosper",
      "Sales": 9.42
  },
  {
      "County": "Grant",
      "Sales": 3.03
  },
  {
      "County": "Greeley",
      "Sales": 11.92
  },
  {
      "County": "Hall",
      "Sales": 290.17
  },
  {
      "County": "Hamilton",
      "Sales": 43.54
  },
  {
      "County": "Harlan",
      "Sales": 16.79
  },
  {
      "County": "Hayes",
      "Sales": 4.66
  },
  {
      "County": "Hitchcock",
      "Sales": 13.72
  },
  {
      "County": "Holt",
      "Sales": 49.93
  },
  {
      "County": "Hooker",
      "Sales": 3.53
  },
  {
      "County": "Howard",
      "Sales": 30.37
  },
  {
      "County": "Jefferson",
      "Sales": 36.13
  },
  {
      "County": "Johnson",
      "Sales": 24.58
  },
  {
      "County": "Kearney",
      "Sales": 31.29
  },
  {
      "County": "Keith",
      "Sales": 38.85
  },
  {
      "County": "Keya Paha",
      "Sales": 3.78
  },
  {
      "County": "Kimball",
      "Sales": 17.69
  },
  {
      "County": "Knox",
      "Sales": 40.93
  },
  {
      "County": "Lancaster",
      "Sales": 1419.49
  },
  {
      "County": "Lincoln",
      "Sales": 172.28
  },
  {
      "County": "Logan",
      "Sales": 3.65
  },
  {
      "County": "Loup",
      "Sales": 2.75
  },
  {
      "County": "McPherson",
      "Sales": 2.51
  },
  {
      "County": "Madison",
      "Sales": 168.59
  },
  {
      "County": "Merrick",
      "Sales": 37.28
  },
  {
      "County": "Morrill",
      "Sales": 23.45
  },
  {
      "County": "Nance",
      "Sales": 17.31
  },
  {
      "County": "Nemaha",
      "Sales": 34.20
  },
  {
      "County": "Nuckolls",
      "Sales": 21.09
  },
  {
      "County": "Otoe",
      "Sales": 75.28
  },
  {
      "County": "Pawnee",
      "Sales": 12.95
  },
  {
      "County": "Perkins",
      "Sales": 13.96
  },
  {
      "County": "Phelps",
      "Sales": 44.03
  },
  {
      "County": "Pierce",
      "Sales": 34.17
  },
  {
      "County": "Platte",
      "Sales": 155.34
  },
  {
      "County": "Polk",
      "Sales": 25.21
  },
  {
      "County": "Red Willow",
      "Sales": 52.60
  },
  {
      "County": "Richardson",
      "Sales": 38.83
  },
  {
      "County": "Rock",
      "Sales": 6.74
  },
  {
      "County": "Saline",
      "Sales": 68.89
  },
  {
      "County": "Sarpy",
      "Sales": 809.20
  },
  {
      "County": "Saunders",
      "Sales": 100.02
  },
  {
      "County": "Scotts Bluff",
      "Sales": 176.09
  },
  {
      "County": "Seward",
      "Sales": 81.67
  },
  {
      "County": "Sheridan",
      "Sales": 25.09
  },
  {
      "County": "Sherman",
      "Sales": 14.84
  },
  {
      "County": "Sioux",
      "Sales": 6.27
  },
  {
      "County": "Stanton",
      "Sales": 29.31
  },
  {
      "County": "Thayer",
      "Sales": 24.80
  },
  {
      "County": "Thomas",
      "Sales": 3.34
  },
  {
      "County": "Thurston",
      "Sales": 32.95
  },
  {
      "County": "Valley",
      "Sales": 20.04
  },
  {
      "County": "Washington",
      "Sales": 96.64
  },
  {
      "County": "Wayne",
      "Sales": 44.97
  },
  {
      "County": "Webster",
      "Sales": 17.62
  },
  {
      "County": "Wheeler",
      "Sales": 3.63
  },
  {
      "County": "York",
      "Sales": 66.34
  }
        ];
    }

    if (state == 'NH') {
        items = [
  {
      "County": "Belknap",
      "Sales": 252.10
  },
  {
      "County": "Carroll",
      "Sales": 198.98
  },
  {
      "County": "Cheshire",
      "Sales": 320.93
  },
  {
      "County": "Coos",
      "Sales": 134.04
  },
  {
      "County": "Grafton",
      "Sales": 375.47
  },
  {
      "County": "Hillsborough",
      "Sales": 1692.35
  },
  {
      "County": "Merrimack",
      "Sales": 615.17
  },
  {
      "County": "Rockingham",
      "Sales": 1253.12
  },
  {
      "County": "Strafford",
      "Sales": 521.94
  },
  {
      "County": "Sullivan",
      "Sales": 180.07
  }
        ];
    }

    if (state == 'NJ') {
        items = [
  {
      "County": "Atlantic",
      "Sales": 1476.75
  },
  {
      "County": "Bergen",
      "Sales": 4953.49
  },
  {
      "County": "Burlington",
      "Sales": 2413.44
  },
  {
      "County": "Camden",
      "Sales": 2745.42
  },
  {
      "County": "Cape May",
      "Sales": 513.36
  },
  {
      "County": "Cumberland",
      "Sales": 842.23
  },
  {
      "County": "Essex",
      "Sales": 4226.72
  },
  {
      "County": "Gloucester",
      "Sales": 1553.85
  },
  {
      "County": "Hudson",
      "Sales": 3534.64
  },
  {
      "County": "Hunterdon",
      "Sales": 675.84
  },
  {
      "County": "Mercer",
      "Sales": 1982.91
  },
  {
      "County": "Middlesex",
      "Sales": 4437.39
  },
  {
      "County": "Monmouth",
      "Sales": 3370.77
  },
  {
      "County": "Morris",
      "Sales": 2673.38
  },
  {
      "County": "Ocean",
      "Sales": 3123.14
  },
  {
      "County": "Passaic",
      "Sales": 2706.97
  },
  {
      "County": "Salem",
      "Sales": 348.85
  },
  {
      "County": "Somerset",
      "Sales": 1769.69
  },
  {
      "County": "Sussex",
      "Sales": 781.53
  },
  {
      "County": "Union",
      "Sales": 2934.94
  },
  {
      "County": "Warren",
      "Sales": 574.82
  }
        ];
    }

    if (state == 'NM') {
        items = [
  {
      "County": "Bernalillo",
      "Sales": 2278.91
  },
  {
      "County": "Catron",
      "Sales": 12.19
  },
  {
      "County": "Chaves",
      "Sales": 222.49
  },
  {
      "County": "Cibola",
      "Sales": 92.39
  },
  {
      "County": "Colfax",
      "Sales": 44.26
  },
  {
      "County": "Curry",
      "Sales": 171.02
  },
  {
      "County": "De Baca",
      "Sales": 6.45
  },
  {
      "County": "Dona Ana",
      "Sales": 721.51
  },
  {
      "County": "Eddy",
      "Sales": 187.50
  },
  {
      "County": "Grant",
      "Sales": 99.13
  },
  {
      "County": "Guadalupe",
      "Sales": 15.38
  },
  {
      "County": "Harding",
      "Sales": 2.34
  },
  {
      "County": "Hidalgo",
      "Sales": 15.73
  },
  {
      "County": "Lea",
      "Sales": 230.05
  },
  {
      "County": "Lincoln",
      "Sales": 67.96
  },
  {
      "County": "Los Alamos",
      "Sales": 60.16
  },
  {
      "County": "Luna",
      "Sales": 83.35
  },
  {
      "County": "McKinley",
      "Sales": 247.79
  },
  {
      "County": "Mora",
      "Sales": 15.90
  },
  {
      "County": "Otero",
      "Sales": 221.79
  },
  {
      "County": "Quay",
      "Sales": 29.28
  },
  {
      "County": "Rio Arriba",
      "Sales": 135.45
  },
  {
      "County": "Roosevelt",
      "Sales": 67.45
  },
  {
      "County": "Sandoval",
      "Sales": 461.63
  },
  {
      "County": "San Juan",
      "Sales": 427.59
  },
  {
      "County": "San Miguel",
      "Sales": 96.47
  },
  {
      "County": "Santa Fe",
      "Sales": 498.30
  },
  {
      "County": "Sierra",
      "Sales": 39.11
  },
  {
      "County": "Socorro",
      "Sales": 59.44
  },
  {
      "County": "Taos",
      "Sales": 111.66
  },
  {
      "County": "Torrance",
      "Sales": 53.12
  },
  {
      "County": "Union",
      "Sales": 14.77
  },
  {
      "County": "Valencia",
      "Sales": 257.84
  }
        ];
    }

    if (state == 'NV') {
        items = [
  {
      "County": "Churchill",
      "Sales": 100.59
  },
  {
      "County": "Clark",
      "Sales": 8477.36
  },
  {
      "County": "Douglas",
      "Sales": 196.97
  },
  {
      "County": "Elko",
      "Sales": 218.99
  },
  {
      "County": "Esmeralda",
      "Sales": 3.48
  },
  {
      "County": "Eureka",
      "Sales": 8.68
  },
  {
      "County": "Humboldt",
      "Sales": 72.58
  },
  {
      "County": "Lander",
      "Sales": 25.22
  },
  {
      "County": "Lincoln",
      "Sales": 21.93
  },
  {
      "County": "Lyon",
      "Sales": 215.53
  },
  {
      "County": "Mineral",
      "Sales": 19.29
  },
  {
      "County": "Nye",
      "Sales": 176.82
  },
  {
      "County": "Pershing",
      "Sales": 28.75
  },
  {
      "County": "Storey",
      "Sales": 16.48
  },
  {
      "County": "Washoe",
      "Sales": 1813.18
  },
  {
      "County": "White Pine",
      "Sales": 42.04
  },
  {
      "County": "Carson City",
      "Sales": 226.08
  }
        ];
    }

    if (state == 'NY') {
        items = [
  {
      "County": "Albany",
      "Sales": 2593.07
  },
  {
      "County": "Allegany",
      "Sales": 406.42
  },
  {
      "County": "Bronx",
      "Sales": 11985.44
  },
  {
      "County": "Broome",
      "Sales": 1668.77
  },
  {
      "County": "Cattaraugus",
      "Sales": 666.48
  },
  {
      "County": "Cayuga",
      "Sales": 671.42
  },
  {
      "County": "Chautauqua",
      "Sales": 1124.26
  },
  {
      "County": "Chemung",
      "Sales": 747.70
  },
  {
      "County": "Chenango",
      "Sales": 418.20
  },
  {
      "County": "Clinton",
      "Sales": 689.28
  },
  {
      "County": "Columbia",
      "Sales": 525.83
  },
  {
      "County": "Cortland",
      "Sales": 413.75
  },
  {
      "County": "Delaware",
      "Sales": 394.71
  },
  {
      "County": "Dutchess",
      "Sales": 2508.34
  },
  {
      "County": "Erie",
      "Sales": 7771.02
  },
  {
      "County": "Essex",
      "Sales": 327.46
  },
  {
      "County": "Franklin",
      "Sales": 436.66
  },
  {
      "County": "Fulton",
      "Sales": 461.14
  },
  {
      "County": "Genesee",
      "Sales": 502.27
  },
  {
      "County": "Greene",
      "Sales": 409.35
  },
  {
      "County": "Hamilton",
      "Sales": 40.32
  },
  {
      "County": "Herkimer",
      "Sales": 542.20
  },
  {
      "County": "Jefferson",
      "Sales": 1009.57
  },
  {
      "County": "Kings",
      "Sales": 21898.45
  },
  {
      "County": "Lewis",
      "Sales": 229.35
  },
  {
      "County": "Livingston",
      "Sales": 546.63
  },
  {
      "County": "Madison",
      "Sales": 611.48
  },
  {
      "County": "Monroe",
      "Sales": 6332.66
  },
  {
      "County": "Montgomery",
      "Sales": 421.53
  },
  {
      "County": "Nassau",
      "Sales": 11422.92
  },
  {
      "County": "New York",
      "Sales": 13737.77
  },
  {
      "County": "Niagara",
      "Sales": 1809.97
  },
  {
      "County": "Oneida",
      "Sales": 1973.32
  },
  {
      "County": "Onondaga",
      "Sales": 3956.93
  },
  {
      "County": "Ontario",
      "Sales": 921.70
  },
  {
      "County": "Orange",
      "Sales": 3173.00
  },
  {
      "County": "Orleans",
      "Sales": 356.80
  },
  {
      "County": "Oswego",
      "Sales": 1023.60
  },
  {
      "County": "Otsego",
      "Sales": 521.10
  },
  {
      "County": "Putnam",
      "Sales": 841.80
  },
  {
      "County": "Queens",
      "Sales": 19398.06
  },
  {
      "County": "Rensselaer",
      "Sales": 1350.99
  },
  {
      "County": "Richmond",
      "Sales": 3992.70
  },
  {
      "County": "Rockland",
      "Sales": 2710.99
  },
  {
      "County": "St. Lawrence",
      "Sales": 945.86
  },
  {
      "County": "Saratoga",
      "Sales": 1891.21
  },
  {
      "County": "Schenectady",
      "Sales": 1312.25
  },
  {
      "County": "Schoharie",
      "Sales": 269.02
  },
  {
      "County": "Schuyler",
      "Sales": 155.95
  },
  {
      "County": "Seneca",
      "Sales": 299.13
  },
  {
      "County": "Steuben",
      "Sales": 833.39
  },
  {
      "County": "Suffolk",
      "Sales": 12669.77
  },
  {
      "County": "Sullivan",
      "Sales": 647.67
  },
  {
      "County": "Tioga",
      "Sales": 424.45
  },
  {
      "County": "Tompkins",
      "Sales": 875.36
  },
  {
      "County": "Ulster",
      "Sales": 1529.07
  },
  {
      "County": "Warren",
      "Sales": 551.97
  },
  {
      "County": "Washington",
      "Sales": 533.01
  },
  {
      "County": "Wayne",
      "Sales": 781.21
  },
  {
      "County": "Westchester",
      "Sales": 8184.43
  },
  {
      "County": "Wyoming",
      "Sales": 350.85
  },
  {
      "County": "Yates",
      "Sales": 212.52
  }
        ];
    }

    if (state == 'OH') {
        items = [
  {
      "County": "Adams",
      "Sales": 170.79
  },
  {
      "County": "Allen",
      "Sales": 639.86
  },
  {
      "County": "Ashland",
      "Sales": 322.33
  },
  {
      "County": "Ashtabula",
      "Sales": 606.52
  },
  {
      "County": "Athens",
      "Sales": 393.05
  },
  {
      "County": "Auglaize",
      "Sales": 279.04
  },
  {
      "County": "Belmont",
      "Sales": 422.76
  },
  {
      "County": "Brown",
      "Sales": 268.98
  },
  {
      "County": "Butler",
      "Sales": 2256.11
  },
  {
      "County": "Carroll",
      "Sales": 171.82
  },
  {
      "County": "Champaign",
      "Sales": 239.76
  },
  {
      "County": "Clark",
      "Sales": 827.45
  },
  {
      "County": "Clermont",
      "Sales": 1216.66
  },
  {
      "County": "Clinton",
      "Sales": 254.89
  },
  {
      "County": "Columbiana",
      "Sales": 643.48
  },
  {
      "County": "Coshocton",
      "Sales": 223.38
  },
  {
      "County": "Crawford",
      "Sales": 260.13
  },
  {
      "County": "Cuyahoga",
      "Sales": 7675.81
  },
  {
      "County": "Darke",
      "Sales": 318.27
  },
  {
      "County": "Defiance",
      "Sales": 234.15
  },
  {
      "County": "Delaware",
      "Sales": 1124.06
  },
  {
      "County": "Erie",
      "Sales": 462.12
  },
  {
      "County": "Fairfield",
      "Sales": 904.62
  },
  {
      "County": "Fayette",
      "Sales": 175.01
  },
  {
      "County": "Franklin",
      "Sales": 7366.56
  },
  {
      "County": "Fulton",
      "Sales": 258.19
  },
  {
      "County": "Gallia",
      "Sales": 186.07
  },
  {
      "County": "Geauga",
      "Sales": 571.04
  },
  {
      "County": "Greene",
      "Sales": 991.74
  },
  {
      "County": "Guernsey",
      "Sales": 240.86
  },
  {
      "County": "Hamilton",
      "Sales": 4888.83
  },
  {
      "County": "Hancock",
      "Sales": 460.45
  },
  {
      "County": "Hardin",
      "Sales": 192.27
  },
  {
      "County": "Harrison",
      "Sales": 94.93
  },
  {
      "County": "Henry",
      "Sales": 170.71
  },
  {
      "County": "Highland",
      "Sales": 263.12
  },
  {
      "County": "Hocking",
      "Sales": 174.19
  },
  {
      "County": "Holmes",
      "Sales": 264.90
  },
  {
      "County": "Huron",
      "Sales": 357.85
  },
  {
      "County": "Jackson",
      "Sales": 199.21
  },
  {
      "County": "Jefferson",
      "Sales": 413.00
  },
  {
      "County": "Knox",
      "Sales": 369.52
  },
  {
      "County": "Lake",
      "Sales": 1396.77
  },
  {
      "County": "Lawrence",
      "Sales": 376.25
  },
  {
      "County": "Licking",
      "Sales": 1023.16
  },
  {
      "County": "Logan",
      "Sales": 276.37
  },
  {
      "County": "Lorain",
      "Sales": 1840.19
  },
  {
      "County": "Lucas",
      "Sales": 2651.83
  },
  {
      "County": "Madison",
      "Sales": 262.98
  },
  {
      "County": "Mahoning",
      "Sales": 1421.15
  },
  {
      "County": "Marion",
      "Sales": 400.48
  },
  {
      "County": "Medina",
      "Sales": 1062.91
  },
  {
      "County": "Meigs",
      "Sales": 142.78
  },
  {
      "County": "Mercer",
      "Sales": 247.83
  },
  {
      "County": "Miami",
      "Sales": 628.57
  },
  {
      "County": "Monroe",
      "Sales": 88.63
  },
  {
      "County": "Montgomery",
      "Sales": 3256.18
  },
  {
      "County": "Morgan",
      "Sales": 90.57
  },
  {
      "County": "Morrow",
      "Sales": 212.89
  },
  {
      "County": "Muskingum",
      "Sales": 517.92
  },
  {
      "County": "Noble",
      "Sales": 88.89
  },
  {
      "County": "Ottawa",
      "Sales": 250.07
  },
  {
      "County": "Paulding",
      "Sales": 117.00
  },
  {
      "County": "Perry",
      "Sales": 218.74
  },
  {
      "County": "Pickaway",
      "Sales": 342.14
  },
  {
      "County": "Pike",
      "Sales": 172.38
  },
  {
      "County": "Portage",
      "Sales": 995.74
  },
  {
      "County": "Preble",
      "Sales": 253.59
  },
  {
      "County": "Putnam",
      "Sales": 207.14
  },
  {
      "County": "Richland",
      "Sales": 739.98
  },
  {
      "County": "Ross",
      "Sales": 473.44
  },
  {
      "County": "Sandusky",
      "Sales": 365.20
  },
  {
      "County": "Scioto",
      "Sales": 474.91
  },
  {
      "County": "Seneca",
      "Sales": 339.77
  },
  {
      "County": "Shelby",
      "Sales": 298.93
  },
  {
      "County": "Stark",
      "Sales": 2281.39
  },
  {
      "County": "Summit",
      "Sales": 3292.50
  },
  {
      "County": "Trumbull",
      "Sales": 1254.49
  },
  {
      "County": "Tuscarawas",
      "Sales": 563.14
  },
  {
      "County": "Union",
      "Sales": 323.92
  },
  {
      "County": "Van Wert",
      "Sales": 172.94
  },
  {
      "County": "Vinton",
      "Sales": 80.67
  },
  {
      "County": "Warren",
      "Sales": 1331.82
  },
  {
      "County": "Washington",
      "Sales": 372.56
  },
  {
      "County": "Wayne",
      "Sales": 699.25
  },
  {
      "County": "Williams",
      "Sales": 227.88
  },
  {
      "County": "Wood",
      "Sales": 785.50
  },
  {
      "County": "Wyandot",
      "Sales": 136.40
  }
        ];
    }

    if (state == 'OK') {
        items = [
  {
      "County": "Adair",
      "Sales": 166.23
  },
  {
      "County": "Alfalfa",
      "Sales": 43.79
  },
  {
      "County": "Atoka",
      "Sales": 104.10
  },
  {
      "County": "Beaver",
      "Sales": 41.69
  },
  {
      "County": "Beckham",
      "Sales": 177.04
  },
  {
      "County": "Blaine",
      "Sales": 72.80
  },
  {
      "County": "Bryan",
      "Sales": 331.39
  },
  {
      "County": "Caddo",
      "Sales": 221.66
  },
  {
      "County": "Canadian",
      "Sales": 944.66
  },
  {
      "County": "Carter",
      "Sales": 363.20
  },
  {
      "County": "Cherokee",
      "Sales": 359.65
  },
  {
      "County": "Choctaw",
      "Sales": 112.69
  },
  {
      "County": "Cimarron",
      "Sales": 17.49
  },
  {
      "County": "Cleveland",
      "Sales": 2017.35
  },
  {
      "County": "Coal",
      "Sales": 43.94
  },
  {
      "County": "Comanche",
      "Sales": 935.78
  },
  {
      "County": "Cotton",
      "Sales": 46.08
  },
  {
      "County": "Craig",
      "Sales": 109.89
  },
  {
      "County": "Creek",
      "Sales": 527.82
  },
  {
      "County": "Custer",
      "Sales": 220.03
  },
  {
      "County": "Delaware",
      "Sales": 309.91
  },
  {
      "County": "Dewey",
      "Sales": 36.28
  },
  {
      "County": "Ellis",
      "Sales": 31.23
  },
  {
      "County": "Garfield",
      "Sales": 466.38
  },
  {
      "County": "Garvin",
      "Sales": 204.73
  },
  {
      "County": "Grady",
      "Sales": 402.10
  },
  {
      "County": "Grant",
      "Sales": 33.91
  },
  {
      "County": "Greer",
      "Sales": 46.22
  },
  {
      "County": "Harmon",
      "Sales": 21.49
  },
  {
      "County": "Harper",
      "Sales": 28.56
  },
  {
      "County": "Haskell",
      "Sales": 97.76
  },
  {
      "County": "Hughes",
      "Sales": 103.53
  },
  {
      "County": "Jackson",
      "Sales": 195.40
  },
  {
      "County": "Jefferson",
      "Sales": 48.18
  },
  {
      "County": "Johnston",
      "Sales": 82.31
  },
  {
      "County": "Kay",
      "Sales": 341.79
  },
  {
      "County": "Kingfisher",
      "Sales": 114.42
  },
  {
      "County": "Kiowa",
      "Sales": 69.96
  },
  {
      "County": "Latimer",
      "Sales": 80.70
  },
  {
      "County": "Le Flore",
      "Sales": 372.81
  },
  {
      "County": "Lincoln",
      "Sales": 257.29
  },
  {
      "County": "Logan",
      "Sales": 332.72
  },
  {
      "County": "Love",
      "Sales": 72.97
  },
  {
      "County": "McClain",
      "Sales": 273.47
  },
  {
      "County": "McCurtain",
      "Sales": 247.66
  },
  {
      "County": "McIntosh",
      "Sales": 153.49
  },
  {
      "County": "Major",
      "Sales": 57.55
  },
  {
      "County": "Marshall",
      "Sales": 119.75
  },
  {
      "County": "Mayes",
      "Sales": 305.62
  },
  {
      "County": "Murray",
      "Sales": 102.70
  },
  {
      "County": "Muskogee",
      "Sales": 526.57
  },
  {
      "County": "Noble",
      "Sales": 85.73
  },
  {
      "County": "Nowata",
      "Sales": 79.06
  },
  {
      "County": "Okfuskee",
      "Sales": 92.70
  },
  {
      "County": "Oklahoma",
      "Sales": 5656.78
  },
  {
      "County": "Okmulgee",
      "Sales": 295.39
  },
  {
      "County": "Osage",
      "Sales": 359.42
  },
  {
      "County": "Ottawa",
      "Sales": 241.51
  },
  {
      "County": "Pawnee",
      "Sales": 123.68
  },
  {
      "County": "Payne",
      "Sales": 592.20
  },
  {
      "County": "Pittsburg",
      "Sales": 334.82
  },
  {
      "County": "Pontotoc",
      "Sales": 284.56
  },
  {
      "County": "Pottawatomie",
      "Sales": 532.97
  },
  {
      "County": "Pushmataha",
      "Sales": 84.14
  },
  {
      "County": "Roger Mills",
      "Sales": 28.04
  },
  {
      "County": "Rogers",
      "Sales": 666.94
  },
  {
      "County": "Seminole",
      "Sales": 190.44
  },
  {
      "County": "Sequoyah",
      "Sales": 308.72
  },
  {
      "County": "Stephens",
      "Sales": 336.44
  },
  {
      "County": "Texas",
      "Sales": 165.39
  },
  {
      "County": "Tillman",
      "Sales": 57.76
  },
  {
      "County": "Tulsa",
      "Sales": 4661.84
  },
  {
      "County": "Wagoner",
      "Sales": 566.99
  },
  {
      "County": "Washington",
      "Sales": 386.31
  },
  {
      "County": "Washita",
      "Sales": 87.47
  },
  {
      "County": "Woods",
      "Sales": 67.72
  },
  {
      "County": "Woodward",
      "Sales": 158.95
  }
        ];
    }

    if (state == 'OR') {
        items = [
  {
      "County": "Baker",
      "Sales": 135.26
  },
  {
      "County": "Benton",
      "Sales": 731.18
  },
  {
      "County": "Clackamas",
      "Sales": 3278.50
  },
  {
      "County": "Clatsop",
      "Sales": 314.49
  },
  {
      "County": "Columbia",
      "Sales": 416.66
  },
  {
      "County": "Coos",
      "Sales": 525.91
  },
  {
      "County": "Crook",
      "Sales": 175.76
  },
  {
      "County": "Curry",
      "Sales": 188.63
  },
  {
      "County": "Deschutes",
      "Sales": 1401.32
  },
  {
      "County": "Douglas",
      "Sales": 903.00
  },
  {
      "County": "Gilliam",
      "Sales": 16.44
  },
  {
      "County": "Grant",
      "Sales": 61.50
  },
  {
      "County": "Harney",
      "Sales": 60.34
  },
  {
      "County": "Hood River",
      "Sales": 191.47
  },
  {
      "County": "Jackson",
      "Sales": 1760.96
  },
  {
      "County": "Jefferson",
      "Sales": 178.55
  },
  {
      "County": "Josephine",
      "Sales": 703.44
  },
  {
      "County": "Klamath",
      "Sales": 556.54
  },
  {
      "County": "Lake",
      "Sales": 66.03
  },
  {
      "County": "Lane",
      "Sales": 3007.86
  },
  {
      "County": "Lincoln",
      "Sales": 391.38
  },
  {
      "County": "Linn",
      "Sales": 1002.85
  },
  {
      "County": "Malheur",
      "Sales": 257.36
  },
  {
      "County": "Marion",
      "Sales": 2732.60
  },
  {
      "County": "Morrow",
      "Sales": 95.72
  },
  {
      "County": "Multnomah",
      "Sales": 6469.25
  },
  {
      "County": "Polk",
      "Sales": 648.45
  },
  {
      "County": "Sherman",
      "Sales": 14.62
  },
  {
      "County": "Tillamook",
      "Sales": 213.78
  },
  {
      "County": "Umatilla",
      "Sales": 647.82
  },
  {
      "County": "Union",
      "Sales": 216.61
  },
  {
      "County": "Wallowa",
      "Sales": 57.54
  },
  {
      "County": "Wasco",
      "Sales": 215.13
  },
  {
      "County": "Washington",
      "Sales": 4686.39
  },
  {
      "County": "Wheeler",
      "Sales": 11.66
  },
  {
      "County": "Yamhill",
      "Sales": 850.52
  }
        ];
    }

    if (state == 'PA') {
        items = [
  {
      "County": "Adams",
      "Sales": 622.56
  },
  {
      "County": "Allegheny",
      "Sales": 7550.25
  },
  {
      "County": "Armstrong",
      "Sales": 417.55
  },
  {
      "County": "Beaver",
      "Sales": 1042.94
  },
  {
      "County": "Bedford",
      "Sales": 300.75
  },
  {
      "County": "Berks",
      "Sales": 2535.22
  },
  {
      "County": "Blair",
      "Sales": 774.41
  },
  {
      "County": "Bradford",
      "Sales": 382.05
  },
  {
      "County": "Bucks",
      "Sales": 3843.87
  },
  {
      "County": "Butler",
      "Sales": 1137.12
  },
  {
      "County": "Cambria",
      "Sales": 861.37
  },
  {
      "County": "Cameron",
      "Sales": 29.96
  },
  {
      "County": "Carbon",
      "Sales": 397.19
  },
  {
      "County": "Centre",
      "Sales": 952.75
  },
  {
      "County": "Chester",
      "Sales": 3123.45
  },
  {
      "County": "Clarion",
      "Sales": 240.05
  },
  {
      "County": "Clearfield",
      "Sales": 497.66
  },
  {
      "County": "Clinton",
      "Sales": 244.95
  },
  {
      "County": "Columbia",
      "Sales": 409.52
  },
  {
      "County": "Crawford",
      "Sales": 535.69
  },
  {
      "County": "Cumberland",
      "Sales": 1478.82
  },
  {
      "County": "Dauphin",
      "Sales": 1661.06
  },
  {
      "County": "Delaware",
      "Sales": 3445.35
  },
  {
      "County": "Elk",
      "Sales": 192.99
  },
  {
      "County": "Erie",
      "Sales": 1718.43
  },
  {
      "County": "Fayette",
      "Sales": 827.65
  },
  {
      "County": "Forest",
      "Sales": 46.78
  },
  {
      "County": "Franklin",
      "Sales": 932.40
  },
  {
      "County": "Fulton",
      "Sales": 89.94
  },
  {
      "County": "Greene",
      "Sales": 231.98
  },
  {
      "County": "Huntingdon",
      "Sales": 280.14
  },
  {
      "County": "Indiana",
      "Sales": 537.95
  },
  {
      "County": "Jefferson",
      "Sales": 275.68
  },
  {
      "County": "Juniata",
      "Sales": 151.85
  },
  {
      "County": "Lackawanna",
      "Sales": 1311.57
  },
  {
      "County": "Lancaster",
      "Sales": 3246.88
  },
  {
      "County": "Lawrence",
      "Sales": 547.68
  },
  {
      "County": "Lebanon",
      "Sales": 830.64
  },
  {
      "County": "Lehigh",
      "Sales": 2177.00
  },
  {
      "County": "Luzerne",
      "Sales": 1962.49
  },
  {
      "County": "Lycoming",
      "Sales": 715.80
  },
  {
      "County": "McKean",
      "Sales": 263.50
  },
  {
      "County": "Mercer",
      "Sales": 706.24
  },
  {
      "County": "Mifflin",
      "Sales": 285.79
  },
  {
      "County": "Monroe",
      "Sales": 1024.75
  },
  {
      "County": "Montgomery",
      "Sales": 4980.52
  },
  {
      "County": "Montour",
      "Sales": 113.67
  },
  {
      "County": "Northampton",
      "Sales": 1837.96
  },
  {
      "County": "Northumberland",
      "Sales": 576.76
  },
  {
      "County": "Perry",
      "Sales": 279.33
  },
  {
      "County": "Philadelphia",
      "Sales": 9522.16
  },
  {
      "County": "Pike",
      "Sales": 346.95
  },
  {
      "County": "Potter",
      "Sales": 107.27
  },
  {
      "County": "Schuylkill",
      "Sales": 900.74
  },
  {
      "County": "Snyder",
      "Sales": 244.40
  },
  {
      "County": "Somerset",
      "Sales": 469.13
  },
  {
      "County": "Sullivan",
      "Sales": 38.94
  },
  {
      "County": "Susquehanna",
      "Sales": 259.25
  },
  {
      "County": "Tioga",
      "Sales": 260.33
  },
  {
      "County": "Union",
      "Sales": 275.07
  },
  {
      "County": "Venango",
      "Sales": 330.49
  },
  {
      "County": "Warren",
      "Sales": 250.66
  },
  {
      "County": "Washington",
      "Sales": 1276.47
  },
  {
      "County": "Wayne",
      "Sales": 316.03
  },
  {
      "County": "Westmoreland",
      "Sales": 2222.03
  },
  {
      "County": "Wyoming",
      "Sales": 171.68
  },
  {
      "County": "York",
      "Sales": 2691.21
  }
        ];
    }

    if (state == 'RI') {
        items = [
  {
      "County": "Bristol",
      "Sales": 437.27
  },
  {
      "County": "Kent",
      "Sales": 1466.15
  },
  {
      "County": "Newport",
      "Sales": 732.01
  },
  {
      "County": "Providence",
      "Sales": 5584.42
  },
  {
      "County": "Washington",
      "Sales": 1121.67
  }
        ];
    }

    if (state == 'SC') {
        items = [
  {
      "County": "Abbeville",
      "Sales": 212.58
  },
  {
      "County": "Aiken",
      "Sales": 1395.61
  },
  {
      "County": "Allendale",
      "Sales": 83.64
  },
  {
      "County": "Anderson",
      "Sales": 1620.58
  },
  {
      "County": "Bamberg",
      "Sales": 131.17
  },
  {
      "County": "Barnwell",
      "Sales": 188.03
  },
  {
      "County": "Beaufort",
      "Sales": 1460.75
  },
  {
      "County": "Berkeley",
      "Sales": 1649.31
  },
  {
      "County": "Calhoun",
      "Sales": 127.98
  },
  {
      "County": "Charleston",
      "Sales": 3169.09
  },
  {
      "County": "Cherokee",
      "Sales": 475.06
  },
  {
      "County": "Chester",
      "Sales": 276.94
  },
  {
      "County": "Chesterfield",
      "Sales": 392.71
  },
  {
      "County": "Clarendon",
      "Sales": 292.04
  },
  {
      "County": "Colleton",
      "Sales": 321.22
  },
  {
      "County": "Darlington",
      "Sales": 577.50
  },
  {
      "County": "Dillon",
      "Sales": 265.47
  },
  {
      "County": "Dorchester",
      "Sales": 1235.98
  },
  {
      "County": "Edgefield",
      "Sales": 224.72
  },
  {
      "County": "Fairfield",
      "Sales": 196.44
  },
  {
      "County": "Florence",
      "Sales": 1175.87
  },
  {
      "County": "Georgetown",
      "Sales": 513.78
  },
  {
      "County": "Greenville",
      "Sales": 4031.60
  },
  {
      "County": "Greenwood",
      "Sales": 592.70
  },
  {
      "County": "Hampton",
      "Sales": 173.48
  },
  {
      "County": "Horry",
      "Sales": 2462.23
  },
  {
      "County": "Jasper",
      "Sales": 226.37
  },
  {
      "County": "Kershaw",
      "Sales": 531.43
  },
  {
      "County": "Lancaster",
      "Sales": 683.95
  },
  {
      "County": "Laurens",
      "Sales": 562.99
  },
  {
      "County": "Lee",
      "Sales": 155.96
  },
  {
      "County": "Lexington",
      "Sales": 2327.09
  },
  {
      "County": "McCormick",
      "Sales": 84.56
  },
  {
      "County": "Marion",
      "Sales": 272.63
  },
  {
      "County": "Marlboro",
      "Sales": 238.05
  },
  {
      "County": "Newberry",
      "Sales": 318.96
  },
  {
      "County": "Oconee",
      "Sales": 637.94
  },
  {
      "County": "Orangeburg",
      "Sales": 773.07
  },
  {
      "County": "Pickens",
      "Sales": 1018.63
  },
  {
      "County": "Richland",
      "Sales": 3393.96
  },
  {
      "County": "Saluda",
      "Sales": 170.79
  },
  {
      "County": "Spartanburg",
      "Sales": 2473.44
  },
  {
      "County": "Sumter",
      "Sales": 919.12
  },
  {
      "County": "Union",
      "Sales": 238.28
  },
  {
      "County": "Williamsburg",
      "Sales": 281.09
  },
  {
      "County": "York",
      "Sales": 2034.76
  }
        ];
    }

    if (state == 'SD') {
        items = [
  {
      "County": "Aurora",
      "Sales": 3.58
  },
  {
      "County": "Beadle",
      "Sales": 23.81
  },
  {
      "County": "Bennett",
      "Sales": 4.55
  },
  {
      "County": "Bon Homme",
      "Sales": 9.28
  },
  {
      "County": "Brookings",
      "Sales": 43.42
  },
  {
      "County": "Brown",
      "Sales": 49.93
  },
  {
      "County": "Brule",
      "Sales": 7.07
  },
  {
      "County": "Buffalo",
      "Sales": 2.67
  },
  {
      "County": "Butte",
      "Sales": 13.61
  },
  {
      "County": "Campbell",
      "Sales": 1.76
  },
  {
      "County": "Charles Mix",
      "Sales": 12.17
  },
  {
      "County": "Clark",
      "Sales": 4.75
  },
  {
      "County": "Clay",
      "Sales": 18.35
  },
  {
      "County": "Codington",
      "Sales": 36.68
  },
  {
      "County": "Corson",
      "Sales": 5.55
  },
  {
      "County": "Custer",
      "Sales": 11.15
  },
  {
      "County": "Davison",
      "Sales": 26.11
  },
  {
      "County": "Day",
      "Sales": 7.37
  },
  {
      "County": "Deuel",
      "Sales": 5.69
  },
  {
      "County": "Dewey",
      "Sales": 7.36
  },
  {
      "County": "Douglas",
      "Sales": 3.98
  },
  {
      "County": "Edmunds",
      "Sales": 5.32
  },
  {
      "County": "Fall River",
      "Sales": 9.01
  },
  {
      "County": "Faulk",
      "Sales": 3.14
  },
  {
      "County": "Grant",
      "Sales": 9.59
  },
  {
      "County": "Gregory",
      "Sales": 5.59
  },
  {
      "County": "Haakon",
      "Sales": 2.49
  },
  {
      "County": "Hamlin",
      "Sales": 7.85
  },
  {
      "County": "Hand",
      "Sales": 4.47
  },
  {
      "County": "Hanson",
      "Sales": 4.48
  },
  {
      "County": "Harding",
      "Sales": 1.66
  },
  {
      "County": "Hughes",
      "Sales": 23.06
  },
  {
      "County": "Hutchinson",
      "Sales": 9.41
  },
  {
      "County": "Hyde",
      "Sales": 1.83
  },
  {
      "County": "Jackson",
      "Sales": 4.24
  },
  {
      "County": "Jerauld",
      "Sales": 2.72
  },
  {
      "County": "Jones",
      "Sales": 1.32
  },
  {
      "County": "Kingsbury",
      "Sales": 6.67
  },
  {
      "County": "Lake",
      "Sales": 15.88
  },
  {
      "County": "Lawrence",
      "Sales": 32.81
  },
  {
      "County": "Lincoln",
      "Sales": 65.67
  },
  {
      "County": "Lyman",
      "Sales": 5.13
  },
  {
      "County": "McCook",
      "Sales": 7.45
  },
  {
      "County": "McPherson",
      "Sales": 3.24
  },
  {
      "County": "Marshall",
      "Sales": 6.27
  },
  {
      "County": "Meade",
      "Sales": 35.83
  },
  {
      "County": "Mellette",
      "Sales": 2.74
  },
  {
      "County": "Miner",
      "Sales": 3.07
  },
  {
      "County": "Minnehaha",
      "Sales": 236.60
  },
  {
      "County": "Moody",
      "Sales": 8.43
  },
  {
      "County": "Pennington",
      "Sales": 139.29
  },
  {
      "County": "Perkins",
      "Sales": 4.00
  },
  {
      "County": "Potter",
      "Sales": 3.15
  },
  {
      "County": "Roberts",
      "Sales": 13.50
  },
  {
      "County": "Sanborn",
      "Sales": 3.06
  },
  {
      "County": "Shannon",
      "Sales": 18.59
  },
  {
      "County": "Spink",
      "Sales": 8.71
  },
  {
      "County": "Stanley",
      "Sales": 3.93
  },
  {
      "County": "Sully",
      "Sales": 1.89
  },
  {
      "County": "Todd",
      "Sales": 13.15
  },
  {
      "County": "Tripp",
      "Sales": 7.24
  },
  {
      "County": "Turner",
      "Sales": 11.01
  },
  {
      "County": "Union",
      "Sales": 19.53
  },
  {
      "County": "Walworth",
      "Sales": 7.28
  },
  {
      "County": "Yankton",
      "Sales": 29.89
  },
  {
      "County": "Ziebach",
      "Sales": 3.73
  }
        ];
    }

    if (state == 'TN') {
        items = [
  {
      "County": "Anderson",
      "Sales": 633.29
  },
  {
      "County": "Bedford",
      "Sales": 384.80
  },
  {
      "County": "Benton",
      "Sales": 136.56
  },
  {
      "County": "Bledsoe",
      "Sales": 107.65
  },
  {
      "County": "Blount",
      "Sales": 1048.74
  },
  {
      "County": "Bradley",
      "Sales": 853.82
  },
  {
      "County": "Campbell",
      "Sales": 337.33
  },
  {
      "County": "Cannon",
      "Sales": 115.48
  },
  {
      "County": "Carroll",
      "Sales": 239.03
  },
  {
      "County": "Carter",
      "Sales": 480.68
  },
  {
      "County": "Cheatham",
      "Sales": 331.07
  },
  {
      "County": "Chester",
      "Sales": 145.21
  },
  {
      "County": "Claiborne",
      "Sales": 264.58
  },
  {
      "County": "Clay",
      "Sales": 65.17
  },
  {
      "County": "Cocke",
      "Sales": 297.43
  },
  {
      "County": "Coffee",
      "Sales": 447.31
  },
  {
      "County": "Crockett",
      "Sales": 122.32
  },
  {
      "County": "Cumberland",
      "Sales": 481.75
  },
  {
      "County": "Davidson",
      "Sales": 5521.25
  },
  {
      "County": "Decatur",
      "Sales": 97.76
  },
  {
      "County": "DeKalb",
      "Sales": 160.66
  },
  {
      "County": "Dickson",
      "Sales": 421.39
  },
  {
      "County": "Dyer",
      "Sales": 320.35
  },
  {
      "County": "Fayette",
      "Sales": 324.35
  },
  {
      "County": "Fentress",
      "Sales": 150.14
  },
  {
      "County": "Franklin",
      "Sales": 344.80
  },
  {
      "County": "Gibson",
      "Sales": 414.61
  },
  {
      "County": "Giles",
      "Sales": 240.99
  },
  {
      "County": "Grainger",
      "Sales": 190.32
  },
  {
      "County": "Greene",
      "Sales": 572.30
  },
  {
      "County": "Grundy",
      "Sales": 113.16
  },
  {
      "County": "Hamblen",
      "Sales": 528.77
  },
  {
      "County": "Hamilton",
      "Sales": 2923.02
  },
  {
      "County": "Hancock",
      "Sales": 55.99
  },
  {
      "County": "Hardeman",
      "Sales": 220.53
  },
  {
      "County": "Hardin",
      "Sales": 218.25
  },
  {
      "County": "Hawkins",
      "Sales": 476.17
  },
  {
      "County": "Haywood",
      "Sales": 152.78
  },
  {
      "County": "Henderson",
      "Sales": 235.13
  },
  {
      "County": "Henry",
      "Sales": 270.03
  },
  {
      "County": "Hickman",
      "Sales": 203.44
  },
  {
      "County": "Houston",
      "Sales": 69.51
  },
  {
      "County": "Humphreys",
      "Sales": 152.94
  },
  {
      "County": "Jackson",
      "Sales": 96.55
  },
  {
      "County": "Jefferson",
      "Sales": 436.96
  },
  {
      "County": "Johnson",
      "Sales": 150.71
  },
  {
      "County": "Knox",
      "Sales": 3727.39
  },
  {
      "County": "Lake",
      "Sales": 64.81
  },
  {
      "County": "Lauderdale",
      "Sales": 233.01
  },
  {
      "County": "Lawrence",
      "Sales": 352.01
  },
  {
      "County": "Lewis",
      "Sales": 100.27
  },
  {
      "County": "Lincoln",
      "Sales": 281.95
  },
  {
      "County": "Loudon",
      "Sales": 422.92
  },
  {
      "County": "McMinn",
      "Sales": 438.79
  },
  {
      "County": "McNairy",
      "Sales": 219.14
  },
  {
      "County": "Macon",
      "Sales": 190.31
  },
  {
      "County": "Madison",
      "Sales": 827.71
  },
  {
      "County": "Marion",
      "Sales": 237.87
  },
  {
      "County": "Marshall",
      "Sales": 260.97
  },
  {
      "County": "Maury",
      "Sales": 702.19
  },
  {
      "County": "Meigs",
      "Sales": 97.66
  },
  {
      "County": "Monroe",
      "Sales": 379.47
  },
  {
      "County": "Montgomery",
      "Sales": 1543.52
  },
  {
      "County": "Moore",
      "Sales": 52.82
  },
  {
      "County": "Morgan",
      "Sales": 183.72
  },
  {
      "County": "Obion",
      "Sales": 260.98
  },
  {
      "County": "Overton",
      "Sales": 185.06
  },
  {
      "County": "Perry",
      "Sales": 65.97
  },
  {
      "County": "Pickett",
      "Sales": 42.67
  },
  {
      "County": "Polk",
      "Sales": 139.92
  },
  {
      "County": "Putnam",
      "Sales": 616.38
  },
  {
      "County": "Rhea",
      "Sales": 272.57
  },
  {
      "County": "Roane",
      "Sales": 444.71
  },
  {
      "County": "Robertson",
      "Sales": 564.89
  },
  {
      "County": "Rutherford",
      "Sales": 2355.95
  },
  {
      "County": "Scott",
      "Sales": 184.56
  },
  {
      "County": "Sequatchie",
      "Sales": 123.07
  },
  {
      "County": "Sevier",
      "Sales": 784.42
  },
  {
      "County": "Shelby",
      "Sales": 7875.80
  },
  {
      "County": "Smith",
      "Sales": 159.90
  },
  {
      "County": "Stewart",
      "Sales": 112.02
  },
  {
      "County": "Sullivan",
      "Sales": 1312.78
  },
  {
      "County": "Sumner",
      "Sales": 1415.84
  },
  {
      "County": "Tipton",
      "Sales": 516.29
  },
  {
      "County": "Trousdale",
      "Sales": 65.62
  },
  {
      "County": "Unicoi",
      "Sales": 151.59
  },
  {
      "County": "Union",
      "Sales": 160.14
  },
  {
      "County": "Van Buren",
      "Sales": 46.80
  },
  {
      "County": "Warren",
      "Sales": 335.04
  },
  {
      "County": "Washington",
      "Sales": 1052.49
  },
  {
      "County": "Wayne",
      "Sales": 142.00
  },
  {
      "County": "Weakley",
      "Sales": 288.80
  },
  {
      "County": "White",
      "Sales": 220.01
  },
  {
      "County": "Williamson",
      "Sales": 1667.44
  },
  {
      "County": "Wilson",
      "Sales": 1022.30
  }
        ];
    }

    if (state == 'TX') {
        items = [
  {
      "County": "Anderson",
      "Sales": 464.03
  },
  {
      "County": "Andrews",
      "Sales": 134.55
  },
  {
      "County": "Angelina",
      "Sales": 700.33
  },
  {
      "County": "Aransas",
      "Sales": 195.07
  },
  {
      "County": "Archer",
      "Sales": 69.53
  },
  {
      "County": "Armstrong",
      "Sales": 15.61
  },
  {
      "County": "Atascosa",
      "Sales": 377.18
  },
  {
      "County": "Austin",
      "Sales": 231.04
  },
  {
      "County": "Bailey",
      "Sales": 56.98
  },
  {
      "County": "Bandera",
      "Sales": 165.00
  },
  {
      "County": "Bastrop",
      "Sales": 607.29
  },
  {
      "County": "Baylor",
      "Sales": 28.95
  },
  {
      "County": "Bee",
      "Sales": 262.69
  },
  {
      "County": "Bell",
      "Sales": 2617.74
  },
  {
      "County": "Bexar",
      "Sales": 14557.53
  },
  {
      "County": "Blanco",
      "Sales": 85.88
  },
  {
      "County": "Borden",
      "Sales": 5.10
  },
  {
      "County": "Bosque",
      "Sales": 143.00
  },
  {
      "County": "Bowie",
      "Sales": 748.75
  },
  {
      "County": "Brazoria",
      "Sales": 2644.96
  },
  {
      "County": "Brazos",
      "Sales": 1627.17
  },
  {
      "County": "Brewster",
      "Sales": 74.37
  },
  {
      "County": "Briscoe",
      "Sales": 12.31
  },
  {
      "County": "Brooks",
      "Sales": 57.96
  },
  {
      "County": "Brown",
      "Sales": 302.34
  },
  {
      "County": "Burleson",
      "Sales": 137.51
  },
  {
      "County": "Burnet",
      "Sales": 350.99
  },
  {
      "County": "Caldwell",
      "Sales": 314.22
  },
  {
      "County": "Calhoun",
      "Sales": 174.65
  },
  {
      "County": "Callahan",
      "Sales": 108.32
  },
  {
      "County": "Cameron",
      "Sales": 3342.03
  },
  {
      "County": "Camp",
      "Sales": 99.42
  },
  {
      "County": "Carson",
      "Sales": 48.14
  },
  {
      "County": "Cass",
      "Sales": 242.93
  },
  {
      "County": "Castro",
      "Sales": 64.31
  },
  {
      "County": "Chambers",
      "Sales": 294.83
  },
  {
      "County": "Cherokee",
      "Sales": 407.49
  },
  {
      "County": "Childress",
      "Sales": 56.82
  },
  {
      "County": "Clay",
      "Sales": 83.88
  },
  {
      "County": "Cochran",
      "Sales": 24.16
  },
  {
      "County": "Coke",
      "Sales": 25.71
  },
  {
      "County": "Coleman",
      "Sales": 68.42
  },
  {
      "County": "Collin",
      "Sales": 6846.05
  },
  {
      "County": "Collingsworth",
      "Sales": 24.82
  },
  {
      "County": "Colorado",
      "Sales": 166.21
  },
  {
      "County": "Comal",
      "Sales": 948.93
  },
  {
      "County": "Comanche",
      "Sales": 109.11
  },
  {
      "County": "Concho",
      "Sales": 32.38
  },
  {
      "County": "Cooke",
      "Sales": 308.09
  },
  {
      "County": "Coryell",
      "Sales": 610.23
  },
  {
      "County": "Cottle",
      "Sales": 11.63
  },
  {
      "County": "Crane",
      "Sales": 38.23
  },
  {
      "County": "Crockett",
      "Sales": 30.49
  },
  {
      "County": "Crosby",
      "Sales": 47.98
  },
  {
      "County": "Culberson",
      "Sales": 18.24
  },
  {
      "County": "Dallam",
      "Sales": 56.52
  },
  {
      "County": "Dallas",
      "Sales": 19865.37
  },
  {
      "County": "Dawson",
      "Sales": 110.61
  },
  {
      "County": "Deaf Smith",
      "Sales": 153.59
  },
  {
      "County": "Delta",
      "Sales": 41.95
  },
  {
      "County": "Denton",
      "Sales": 5837.07
  },
  {
      "County": "DeWitt",
      "Sales": 164.21
  },
  {
      "County": "Dickens",
      "Sales": 18.35
  },
  {
      "County": "Dimmit",
      "Sales": 87.28
  },
  {
      "County": "Donley",
      "Sales": 28.21
  },
  {
      "County": "Duval",
      "Sales": 93.23
  },
  {
      "County": "Eastland",
      "Sales": 146.13
  },
  {
      "County": "Ector",
      "Sales": 1196.39
  },
  {
      "County": "Edwards",
      "Sales": 15.09
  },
  {
      "County": "Ellis",
      "Sales": 1249.24
  },
  {
      "County": "El Paso",
      "Sales": 6629.33
  },
  {
      "County": "Erath",
      "Sales": 317.63
  },
  {
      "County": "Falls",
      "Sales": 140.10
  },
  {
      "County": "Fannin",
      "Sales": 269.58
  },
  {
      "County": "Fayette",
      "Sales": 198.80
  },
  {
      "County": "Fisher",
      "Sales": 30.88
  },
  {
      "County": "Floyd",
      "Sales": 49.90
  },
  {
      "County": "Foard",
      "Sales": 10.23
  },
  {
      "County": "Fort Bend",
      "Sales": 5224.90
  },
  {
      "County": "Franklin",
      "Sales": 85.38
  },
  {
      "County": "Freestone",
      "Sales": 157.35
  },
  {
      "County": "Frio",
      "Sales": 144.69
  },
  {
      "County": "Gaines",
      "Sales": 151.54
  },
  {
      "County": "Galveston",
      "Sales": 2457.07
  },
  {
      "County": "Garza",
      "Sales": 50.59
  },
  {
      "County": "Gillespie",
      "Sales": 203.09
  },
  {
      "County": "Glasscock",
      "Sales": 10.02
  },
  {
      "County": "Goliad",
      "Sales": 59.79
  },
  {
      "County": "Gonzales",
      "Sales": 162.68
  },
  {
      "County": "Gray",
      "Sales": 184.56
  },
  {
      "County": "Grayson",
      "Sales": 979.94
  },
  {
      "County": "Gregg",
      "Sales": 985.32
  },
  {
      "County": "Grimes",
      "Sales": 215.12
  },
  {
      "County": "Guadalupe",
      "Sales": 1146.78
  },
  {
      "County": "Hale",
      "Sales": 286.44
  },
  {
      "County": "Hall",
      "Sales": 25.94
  },
  {
      "County": "Hamilton",
      "Sales": 66.56
  },
  {
      "County": "Hansford",
      "Sales": 44.49
  },
  {
      "County": "Hardeman",
      "Sales": 32.16
  },
  {
      "County": "Hardin",
      "Sales": 443.84
  },
  {
      "County": "Harris",
      "Sales": 34734.55
  },
  {
      "County": "Harrison",
      "Sales": 535.70
  },
  {
      "County": "Hartley",
      "Sales": 48.86
  },
  {
      "County": "Haskell",
      "Sales": 47.05
  },
  {
      "County": "Hays",
      "Sales": 1409.82
  },
  {
      "County": "Hemphill",
      "Sales": 33.30
  },
  {
      "County": "Henderson",
      "Sales": 630.12
  },
  {
      "County": "Hidalgo",
      "Sales": 6535.44
  },
  {
      "County": "Hill",
      "Sales": 278.90
  },
  {
      "County": "Hockley",
      "Sales": 188.46
  },
  {
      "County": "Hood",
      "Sales": 423.72
  },
  {
      "County": "Hopkins",
      "Sales": 284.85
  },
  {
      "County": "Houston",
      "Sales": 183.50
  },
  {
      "County": "Howard",
      "Sales": 289.51
  },
  {
      "County": "Hudspeth",
      "Sales": 26.57
  },
  {
      "County": "Hunt",
      "Sales": 697.18
  },
  {
      "County": "Hutchinson",
      "Sales": 174.75
  },
  {
      "County": "Irion",
      "Sales": 12.91
  },
  {
      "County": "Jack",
      "Sales": 71.74
  },
  {
      "County": "Jackson",
      "Sales": 116.86
  },
  {
      "County": "Jasper",
      "Sales": 285.52
  },
  {
      "County": "Jeff Davis",
      "Sales": 18.04
  },
  {
      "County": "Jefferson",
      "Sales": 2021.18
  },
  {
      "County": "Jim Hogg",
      "Sales": 42.01
  },
  {
      "County": "Jim Wells",
      "Sales": 333.82
  },
  {
      "County": "Johnson",
      "Sales": 1239.07
  },
  {
      "County": "Jones",
      "Sales": 159.05
  },
  {
      "County": "Karnes",
      "Sales": 120.79
  },
  {
      "County": "Kaufman",
      "Sales": 869.54
  },
  {
      "County": "Kendall",
      "Sales": 302.47
  },
  {
      "County": "Kenedy",
      "Sales": 3.30
  },
  {
      "County": "Kent",
      "Sales": 6.46
  },
  {
      "County": "Kerr",
      "Sales": 400.08
  },
  {
      "County": "Kimble",
      "Sales": 35.89
  },
  {
      "County": "King",
      "Sales": 2.28
  },
  {
      "County": "Kinney",
      "Sales": 28.72
  },
  {
      "County": "Kleberg",
      "Sales": 257.10
  },
  {
      "County": "Knox",
      "Sales": 30.17
  },
  {
      "County": "Lamar",
      "Sales": 395.86
  },
  {
      "County": "Lamb",
      "Sales": 110.33
  },
  {
      "County": "Lampasas",
      "Sales": 161.96
  },
  {
      "County": "La Salle",
      "Sales": 59.02
  },
  {
      "County": "Lavaca",
      "Sales": 156.83
  },
  {
      "County": "Lee",
      "Sales": 133.18
  },
  {
      "County": "Leon",
      "Sales": 134.09
  },
  {
      "County": "Liberty",
      "Sales": 615.96
  },
  {
      "County": "Limestone",
      "Sales": 186.82
  },
  {
      "County": "Lipscomb",
      "Sales": 27.91
  },
  {
      "County": "Live Oak",
      "Sales": 95.04
  },
  {
      "County": "Llano",
      "Sales": 155.73
  },
  {
      "County": "Loving",
      "Sales": 0.76
  },
  {
      "County": "Lubbock",
      "Sales": 2317.24
  },
  {
      "County": "Lynn",
      "Sales": 45.84
  },
  {
      "County": "McCulloch",
      "Sales": 66.72
  },
  {
      "County": "McLennan",
      "Sales": 1934.06
  },
  {
      "County": "McMullen",
      "Sales": 6.12
  },
  {
      "County": "Madison",
      "Sales": 110.37
  },
  {
      "County": "Marion",
      "Sales": 81.97
  },
  {
      "County": "Martin",
      "Sales": 42.54
  },
  {
      "County": "Mason",
      "Sales": 33.06
  },
  {
      "County": "Matagorda",
      "Sales": 293.07
  },
  {
      "County": "Maverick",
      "Sales": 447.97
  },
  {
      "County": "Medina",
      "Sales": 379.63
  },
  {
      "County": "Menard",
      "Sales": 17.20
  },
  {
      "County": "Midland",
      "Sales": 1213.13
  },
  {
      "County": "Milam",
      "Sales": 193.56
  },
  {
      "County": "Mills",
      "Sales": 39.30
  },
  {
      "County": "Mitchell",
      "Sales": 75.30
  },
  {
      "County": "Montague",
      "Sales": 156.20
  },
  {
      "County": "Montgomery",
      "Sales": 3997.67
  },
  {
      "County": "Moore",
      "Sales": 177.33
  },
  {
      "County": "Morris",
      "Sales": 102.79
  },
  {
      "County": "Motley",
      "Sales": 9.58
  },
  {
      "County": "Nacogdoches",
      "Sales": 523.24
  },
  {
      "County": "Navarro",
      "Sales": 384.74
  },
  {
      "County": "Newton",
      "Sales": 113.25
  },
  {
      "County": "Nolan",
      "Sales": 120.43
  },
  {
      "County": "Nueces",
      "Sales": 2820.08
  },
  {
      "County": "Ochiltree",
      "Sales": 86.55
  },
  {
      "County": "Oldham",
      "Sales": 16.84
  },
  {
      "County": "Orange",
      "Sales": 664.42
  },
  {
      "County": "Palo Pinto",
      "Sales": 223.37
  },
  {
      "County": "Panola",
      "Sales": 191.18
  },
  {
      "County": "Parker",
      "Sales": 972.46
  },
  {
      "County": "Parmer",
      "Sales": 79.81
  },
  {
      "County": "Pecos",
      "Sales": 125.72
  },
  {
      "County": "Polk",
      "Sales": 366.74
  },
  {
      "County": "Potter",
      "Sales": 974.40
  },
  {
      "County": "Presidio",
      "Sales": 57.67
  },
  {
      "County": "Rains",
      "Sales": 88.62
  },
  {
      "County": "Randall",
      "Sales": 1012.95
  },
  {
      "County": "Reagan",
      "Sales": 28.84
  },
  {
      "County": "Real",
      "Sales": 26.83
  },
  {
      "County": "Red River",
      "Sales": 99.87
  },
  {
      "County": "Reeves",
      "Sales": 111.85
  },
  {
      "County": "Refugio",
      "Sales": 58.51
  },
  {
      "County": "Roberts",
      "Sales": 6.66
  },
  {
      "County": "Robertson",
      "Sales": 132.04
  },
  {
      "County": "Rockwall",
      "Sales": 682.74
  },
  {
      "County": "Runnels",
      "Sales": 82.57
  },
  {
      "County": "Rusk",
      "Sales": 429.47
  },
  {
      "County": "Sabine",
      "Sales": 82.98
  },
  {
      "County": "San Augustine",
      "Sales": 70.23
  },
  {
      "County": "San Jacinto",
      "Sales": 215.09
  },
  {
      "County": "San Patricio",
      "Sales": 529.70
  },
  {
      "County": "San Saba",
      "Sales": 48.15
  },
  {
      "County": "Schleicher",
      "Sales": 25.68
  },
  {
      "County": "Scurry",
      "Sales": 138.57
  },
  {
      "County": "Shackelford",
      "Sales": 27.03
  },
  {
      "County": "Shelby",
      "Sales": 206.57
  },
  {
      "County": "Sherman",
      "Sales": 24.77
  },
  {
      "County": "Smith",
      "Sales": 1730.62
  },
  {
      "County": "Somervell",
      "Sales": 69.34
  },
  {
      "County": "Starr",
      "Sales": 496.27
  },
  {
      "County": "Stephens",
      "Sales": 74.06
  },
  {
      "County": "Sterling",
      "Sales": 9.76
  },
  {
      "County": "Stonewall",
      "Sales": 11.47
  },
  {
      "County": "Sutton",
      "Sales": 32.08
  },
  {
      "County": "Swisher",
      "Sales": 62.18
  },
  {
      "County": "Tarrant",
      "Sales": 15309.84
  },
  {
      "County": "Taylor",
      "Sales": 1074.16
  },
  {
      "County": "Terrell",
      "Sales": 7.23
  },
  {
      "County": "Terry",
      "Sales": 102.06
  },
  {
      "County": "Throckmorton",
      "Sales": 12.81
  },
  {
      "County": "Titus",
      "Sales": 260.95
  },
  {
      "County": "Tom Green",
      "Sales": 920.68
  },
  {
      "County": "Travis",
      "Sales": 8977.90
  },
  {
      "County": "Trinity",
      "Sales": 115.28
  },
  {
      "County": "Tyler",
      "Sales": 171.91
  },
  {
      "County": "Upshur",
      "Sales": 319.44
  },
  {
      "County": "Upton",
      "Sales": 27.01
  },
  {
      "County": "Uvalde",
      "Sales": 215.65
  },
  {
      "County": "Val Verde",
      "Sales": 389.43
  },
  {
      "County": "Van Zandt",
      "Sales": 420.33
  },
  {
      "County": "Victoria",
      "Sales": 721.05
  },
  {
      "County": "Walker",
      "Sales": 551.17
  },
  {
      "County": "Waller",
      "Sales": 362.12
  },
  {
      "County": "Ward",
      "Sales": 90.05
  },
  {
      "County": "Washington",
      "Sales": 273.49
  },
  {
      "County": "Webb",
      "Sales": 2102.36
  },
  {
      "County": "Wharton",
      "Sales": 330.11
  },
  {
      "County": "Wheeler",
      "Sales": 46.06
  },
  {
      "County": "Wichita",
      "Sales": 1057.59
  },
  {
      "County": "Wilbarger",
      "Sales": 105.17
  },
  {
      "County": "Willacy",
      "Sales": 175.57
  },
  {
      "County": "Williamson",
      "Sales": 3772.43
  },
  {
      "County": "Wilson",
      "Sales": 363.76
  },
  {
      "County": "Winkler",
      "Sales": 60.92
  },
  {
      "County": "Wise",
      "Sales": 488.07
  },
  {
      "County": "Wood",
      "Sales": 338.84
  },
  {
      "County": "Yoakum",
      "Sales": 65.55
  },
  {
      "County": "Young",
      "Sales": 146.90
  },
  {
      "County": "Zapata",
      "Sales": 115.25
  },
  {
      "County": "Zavala",
      "Sales": 97.36
  }
        ];
    }

    if (state == 'UT') {
        items = [
  {
      "County": "Beaver",
      "Sales": 29.99
  },
  {
      "County": "Box Elder",
      "Sales": 235.84
  },
  {
      "County": "Cache",
      "Sales": 542.82
  },
  {
      "County": "Carbon",
      "Sales": 97.45
  },
  {
      "County": "Daggett",
      "Sales": 5.23
  },
  {
      "County": "Davis",
      "Sales": 1495.51
  },
  {
      "County": "Duchesne",
      "Sales": 94.29
  },
  {
      "County": "Emery",
      "Sales": 49.91
  },
  {
      "County": "Garfield",
      "Sales": 23.60
  },
  {
      "County": "Grand",
      "Sales": 43.46
  },
  {
      "County": "Iron",
      "Sales": 217.20
  },
  {
      "County": "Juab",
      "Sales": 48.05
  },
  {
      "County": "Kane",
      "Sales": 33.71
  },
  {
      "County": "Millard",
      "Sales": 58.79
  },
  {
      "County": "Morgan",
      "Sales": 47.23
  },
  {
      "County": "Piute",
      "Sales": 7.01
  },
  {
      "County": "Rich",
      "Sales": 10.62
  },
  {
      "County": "Salt Lake",
      "Sales": 5013.24
  },
  {
      "County": "San Juan",
      "Sales": 69.52
  },
  {
      "County": "Sanpete",
      "Sales": 131.11
  },
  {
      "County": "Sevier",
      "Sales": 96.82
  },
  {
      "County": "Summit",
      "Sales": 178.69
  },
  {
      "County": "Tooele",
      "Sales": 282.12
  },
  {
      "County": "Uintah",
      "Sales": 165.09
  },
  {
      "County": "Utah",
      "Sales": 2562.48
  },
  {
      "County": "Wasatch",
      "Sales": 122.75
  },
  {
      "County": "Washington",
      "Sales": 686.25
  },
  {
      "County": "Wayne",
      "Sales": 12.75
  },
  {
      "County": "Weber",
      "Sales": 1107.47
  }
        ];
    }

    if (state == 'VA') {
        items = [
  {
      "County": "Accomack",
      "Sales": 167.62
  },
  {
      "County": "Albemarle",
      "Sales": 520.84
  },
  {
      "County": "Alleghany",
      "Sales": 81.72
  },
  {
      "County": "Amelia",
      "Sales": 64.45
  },
  {
      "County": "Amherst",
      "Sales": 162.72
  },
  {
      "County": "Appomattox",
      "Sales": 77.14
  },
  {
      "County": "Arlington",
      "Sales": 1137.29
  },
  {
      "County": "Augusta",
      "Sales": 373.75
  },
  {
      "County": "Bath",
      "Sales": 23.34
  },
  {
      "County": "Bedford",
      "Sales": 353.09
  },
  {
      "County": "Bland",
      "Sales": 34.06
  },
  {
      "County": "Botetourt",
      "Sales": 166.88
  },
  {
      "County": "Brunswick",
      "Sales": 85.83
  },
  {
      "County": "Buchanan",
      "Sales": 119.32
  },
  {
      "County": "Buckingham",
      "Sales": 86.65
  },
  {
      "County": "Campbell",
      "Sales": 279.31
  },
  {
      "County": "Caroline",
      "Sales": 148.15
  },
  {
      "County": "Carroll",
      "Sales": 151.11
  },
  {
      "County": "Charles City",
      "Sales": 36.05
  },
  {
      "County": "Charlotte",
      "Sales": 62.22
  },
  {
      "County": "Chesterfield",
      "Sales": 1657.31
  },
  {
      "County": "Clarke",
      "Sales": 72.55
  },
  {
      "County": "Craig",
      "Sales": 26.35
  },
  {
      "County": "Culpeper",
      "Sales": 245.28
  },
  {
      "County": "Cumberland",
      "Sales": 49.76
  },
  {
      "County": "Dickenson",
      "Sales": 78.31
  },
  {
      "County": "Dinwiddie",
      "Sales": 141.10
  },
  {
      "County": "Essex",
      "Sales": 56.78
  },
  {
      "County": "Fairfax",
      "Sales": 5718.76
  },
  {
      "County": "Fauquier",
      "Sales": 339.85
  },
  {
      "County": "Floyd",
      "Sales": 78.52
  },
  {
      "County": "Fluvanna",
      "Sales": 131.36
  },
  {
      "County": "Franklin",
      "Sales": 284.87
  },
  {
      "County": "Frederick",
      "Sales": 411.21
  },
  {
      "County": "Giles",
      "Sales": 85.58
  },
  {
      "County": "Gloucester",
      "Sales": 186.26
  },
  {
      "County": "Goochland",
      "Sales": 109.36
  },
  {
      "County": "Grayson",
      "Sales": 76.66
  },
  {
      "County": "Greene",
      "Sales": 95.09
  },
  {
      "County": "Greensville",
      "Sales": 60.10
  },
  {
      "County": "Halifax",
      "Sales": 179.01
  },
  {
      "County": "Hanover",
      "Sales": 512.40
  },
  {
      "County": "Henrico",
      "Sales": 1611.13
  },
  {
      "County": "Henry",
      "Sales": 266.07
  },
  {
      "County": "Highland",
      "Sales": 11.20
  },
  {
      "County": "Isle of Wight",
      "Sales": 180.30
  },
  {
      "County": "James City",
      "Sales": 356.58
  },
  {
      "County": "King and Queen",
      "Sales": 36.05
  },
  {
      "County": "King George",
      "Sales": 126.04
  },
  {
      "County": "King William",
      "Sales": 81.40
  },
  {
      "County": "Lancaster",
      "Sales": 56.37
  },
  {
      "County": "Lee",
      "Sales": 127.35
  },
  {
      "County": "Loudoun",
      "Sales": 1768.23
  },
  {
      "County": "Louisa",
      "Sales": 171.65
  },
  {
      "County": "Lunenburg",
      "Sales": 63.35
  },
  {
      "County": "Madison",
      "Sales": 66.75
  },
  {
      "County": "Mathews",
      "Sales": 44.99
  },
  {
      "County": "Mecklenburg",
      "Sales": 158.91
  },
  {
      "County": "Middlesex",
      "Sales": 54.42
  },
  {
      "County": "Montgomery",
      "Sales": 486.49
  },
  {
      "County": "Nelson",
      "Sales": 74.78
  },
  {
      "County": "New Kent",
      "Sales": 98.64
  },
  {
      "County": "Northampton",
      "Sales": 61.31
  },
  {
      "County": "Northumberland",
      "Sales": 61.69
  },
  {
      "County": "Nottoway",
      "Sales": 79.76
  },
  {
      "County": "Orange",
      "Sales": 175.41
  },
  {
      "County": "Page",
      "Sales": 120.46
  },
  {
      "County": "Patrick",
      "Sales": 92.88
  },
  {
      "County": "Pittsylvania",
      "Sales": 315.67
  },
  {
      "County": "Powhatan",
      "Sales": 142.90
  },
  {
      "County": "Prince Edward",
      "Sales": 115.30
  },
  {
      "County": "Prince George",
      "Sales": 188.38
  },
  {
      "County": "Prince William",
      "Sales": 2217.78
  },
  {
      "County": "Pulaski",
      "Sales": 174.49
  },
  {
      "County": "Rappahannock",
      "Sales": 37.81
  },
  {
      "County": "Richmond",
      "Sales": 45.27
  },
  {
      "County": "Roanoke",
      "Sales": 472.92
  },
  {
      "County": "Rockbridge",
      "Sales": 112.80
  },
  {
      "County": "Rockingham",
      "Sales": 393.11
  },
  {
      "County": "Russell",
      "Sales": 142.92
  },
  {
      "County": "Scott",
      "Sales": 114.48
  },
  {
      "County": "Shenandoah",
      "Sales": 215.84
  },
  {
      "County": "Smyth",
      "Sales": 160.06
  },
  {
      "County": "Southampton",
      "Sales": 91.67
  },
  {
      "County": "Spotsylvania",
      "Sales": 643.96
  },
  {
      "County": "Stafford",
      "Sales": 691.70
  },
  {
      "County": "Surry",
      "Sales": 34.21
  },
  {
      "County": "Sussex",
      "Sales": 59.72
  },
  {
      "County": "Tazewell",
      "Sales": 223.02
  },
  {
      "County": "Warren",
      "Sales": 195.69
  },
  {
      "County": "Washington",
      "Sales": 277.65
  },
  {
      "County": "Westmoreland",
      "Sales": 89.06
  },
  {
      "County": "Wise",
      "Sales": 205.25
  },
  {
      "County": "Wythe",
      "Sales": 148.38
  },
  {
      "County": "York",
      "Sales": 335.10
  },
  {
      "County": "Alexandria city",
      "Sales": 752.90
  },
  {
      "County": "Bedford city",
      "Sales": 30.08
  },
  {
      "County": "Bristol city",
      "Sales": 87.69
  },
  {
      "County": "Buena Vista city",
      "Sales": 33.78
  },
  {
      "County": "Charlottesville city",
      "Sales": 224.26
  },
  {
      "County": "Chesapeake city",
      "Sales": 1165.93
  },
  {
      "County": "Colonial Heights city",
      "Sales": 89.17
  },
  {
      "County": "Covington city",
      "Sales": 29.42
  },
  {
      "County": "Danville city",
      "Sales": 216.97
  },
  {
      "County": "Emporia city",
      "Sales": 28.26
  },
  {
      "County": "Fairfax city",
      "Sales": 121.22
  },
  {
      "County": "Falls Church city",
      "Sales": 68.31
  },
  {
      "County": "Franklin city",
      "Sales": 43.68
  },
  {
      "County": "Fredericksburg city",
      "Sales": 142.26
  },
  {
      "County": "Galax city",
      "Sales": 35.57
  },
  {
      "County": "Hampton city",
      "Sales": 691.25
  },
  {
      "County": "Harrisonburg city",
      "Sales": 259.89
  },
  {
      "County": "Hopewell city",
      "Sales": 112.07
  },
  {
      "County": "Lexington city",
      "Sales": 36.26
  },
  {
      "County": "Lynchburg city",
      "Sales": 394.49
  },
  {
      "County": "Manassas city",
      "Sales": 210.89
  },
  {
      "County": "Manassas Park city",
      "Sales": 81.66
  },
  {
      "County": "Martinsville city",
      "Sales": 69.56
  },
  {
      "County": "Newport News city",
      "Sales": 920.42
  },
  {
      "County": "Norfolk city",
      "Sales": 1244.66
  },
  {
      "County": "Norton city",
      "Sales": 20.31
  },
  {
      "County": "Petersburg city",
      "Sales": 164.54
  },
  {
      "County": "Poquoson city",
      "Sales": 61.21
  },
  {
      "County": "Portsmouth city",
      "Sales": 486.48
  },
  {
      "County": "Radford city",
      "Sales": 86.89
  },
  {
      "County": "Richmond city",
      "Sales": 1082.71
  },
  {
      "County": "Roanoke city",
      "Sales": 497.91
  },
  {
      "County": "Salem city",
      "Sales": 127.93
  },
  {
      "County": "Staunton city",
      "Sales": 123.13
  },
  {
      "County": "Suffolk city",
      "Sales": 433.50
  },
  {
      "County": "Virginia Beach city",
      "Sales": 2267.83
  },
  {
      "County": "Waynesboro city",
      "Sales": 107.52
  },
  {
      "County": "Williamsburg city",
      "Sales": 76.89
  },
  {
      "County": "Winchester city",
      "Sales": 137.62
  }
        ];
    }

    if (state == 'VT') {
        items = [
  {
      "County": "Addison",
      "Sales": 590.53
  },
  {
      "County": "Bennington",
      "Sales": 588.42
  },
  {
      "County": "Caledonia",
      "Sales": 500.10
  },
  {
      "County": "Chittenden",
      "Sales": 2560.39
  },
  {
      "County": "Essex",
      "Sales": 99.69
  },
  {
      "County": "Franklin",
      "Sales": 775.17
  },
  {
      "County": "Grand Isle",
      "Sales": 112.15
  },
  {
      "County": "Lamoille",
      "Sales": 402.35
  },
  {
      "County": "Orange",
      "Sales": 464.12
  },
  {
      "County": "Orleans",
      "Sales": 436.09
  },
  {
      "County": "Rutland",
      "Sales": 973.05
  },
  {
      "County": "Washington",
      "Sales": 952.13
  },
  {
      "County": "Windham",
      "Sales": 703.95
  },
  {
      "County": "Windsor",
      "Sales": 899.94
  }
        ];
    }


    if (state == 'WA') {
        items = [
  {
      "County": "Adams",
      "Sales": 27976.70
  },
  {
      "County": "Asotin",
      "Sales": 32441.64
  },
  {
      "County": "Benton",
      "Sales": 270693.31
  },
  {
      "County": "Chelan",
      "Sales": 108530.58
  },
  {
      "County": "Clallam",
      "Sales": 106102.22
  },
  {
      "County": "Clark",
      "Sales": 651205.47
  },
  {
      "County": "Columbia",
      "Sales": 5916.09
  },
  {
      "County": "Cowlitz",
      "Sales": 149457.52
  },
  {
      "County": "Douglas",
      "Sales": 57915.16
  },
  {
      "County": "Ferry",
      "Sales": 11218.85
  },
  {
      "County": "Franklin",
      "Sales": 127122.53
  },
  {
      "County": "Garfield",
      "Sales": 3310.19
  },
  {
      "County": "Grant",
      "Sales": 134811.10
  },
  {
      "County": "Grays Harbor",
      "Sales": 104291.59
  },
  {
      "County": "Island",
      "Sales": 115623.43
  },
  {
      "County": "Jefferson",
      "Sales": 44130.03
  },
  {
      "County": "King",
      "Sales": 2999786.78
  },
  {
      "County": "Kitsap",
      "Sales": 372643.12
  },
  {
      "County": "Kittitas",
      "Sales": 61281.11
  },
  {
      "County": "Klickitat",
      "Sales": 30616.34
  },
  {
      "County": "Lewis",
      "Sales": 110165.13
  },
  {
      "County": "Lincoln",
      "Sales": 15114.49
  },
  {
      "County": "Mason",
      "Sales": 88766.26
  },
  {
      "County": "Okanogan",
      "Sales": 60441.82
  },
  {
      "County": "Pacific",
      "Sales": 30076.38
  },
  {
      "County": "Pend Oreille",
      "Sales": 18922.09
  },
  {
      "County": "Pierce",
      "Sales": 1202795.58
  },
  {
      "County": "San Juan",
      "Sales": 23293.13
  },
  {
      "County": "Skagit",
      "Sales": 174367.60
  },
  {
      "County": "Skamania",
      "Sales": 16542.16
  },
  {
      "County": "Snohomish",
      "Sales": 1094466.02
  },
  {
      "County": "Spokane",
      "Sales": 703412.89
  },
  {
      "County": "Stevens",
      "Sales": 63724.13
  },
  {
      "County": "Thurston",
      "Sales": 384997.65
  },
  {
      "County": "Wahkiakum",
      "Sales": 5930.76
  },
  {
      "County": "Walla Walla",
      "Sales": 87347.40
  },
  {
      "County": "Whatcom",
      "Sales": 302778.40
  },
  {
      "County": "Whitman",
      "Sales": 68331.40
  },
  {
      "County": "Yakima",
      "Sales": 362483.64
  }
        ];
    }

    if (state == 'WI') {
        items = [
  {
      "County": "Adams",
      "Sales": 230.81
  },
  {
      "County": "Ashland",
      "Sales": 180.50
  },
  {
      "County": "Barron",
      "Sales": 514.76
  },
  {
      "County": "Bayfield",
      "Sales": 170.81
  },
  {
      "County": "Brown",
      "Sales": 2869.13
  },
  {
      "County": "Buffalo",
      "Sales": 150.53
  },
  {
      "County": "Burnett",
      "Sales": 172.80
  },
  {
      "County": "Calumet",
      "Sales": 559.17
  },
  {
      "County": "Chippewa",
      "Sales": 711.49
  },
  {
      "County": "Clark",
      "Sales": 390.10
  },
  {
      "County": "Columbia",
      "Sales": 638.47
  },
  {
      "County": "Crawford",
      "Sales": 184.79
  },
  {
      "County": "Dane",
      "Sales": 5746.91
  },
  {
      "County": "Dodge",
      "Sales": 995.62
  },
  {
      "County": "Door",
      "Sales": 314.38
  },
  {
      "County": "Douglas",
      "Sales": 494.60
  },
  {
      "County": "Dunn",
      "Sales": 497.25
  },
  {
      "County": "Eau Claire",
      "Sales": 1143.19
  },
  {
      "County": "Florence",
      "Sales": 50.94
  },
  {
      "County": "Fond du Lac",
      "Sales": 1147.24
  },
  {
      "County": "Forest",
      "Sales": 102.85
  },
  {
      "County": "Grant",
      "Sales": 575.54
  },
  {
      "County": "Green",
      "Sales": 418.00
  },
  {
      "County": "Green Lake",
      "Sales": 213.66
  },
  {
      "County": "Iowa",
      "Sales": 267.65
  },
  {
      "County": "Iron",
      "Sales": 66.33
  },
  {
      "County": "Jackson",
      "Sales": 232.65
  },
  {
      "County": "Jefferson",
      "Sales": 952.40
  },
  {
      "County": "Juneau",
      "Sales": 299.18
  },
  {
      "County": "Kenosha",
      "Sales": 1890.59
  },
  {
      "County": "Kewaunee",
      "Sales": 231.09
  },
  {
      "County": "La Crosse",
      "Sales": 1315.33
  },
  {
      "County": "Lafayette",
      "Sales": 188.95
  },
  {
      "County": "Langlade",
      "Sales": 220.61
  },
  {
      "County": "Lincoln",
      "Sales": 323.26
  },
  {
      "County": "Manitowoc",
      "Sales": 908.95
  },
  {
      "County": "Marathon",
      "Sales": 1526.11
  },
  {
      "County": "Marinette",
      "Sales": 468.94
  },
  {
      "County": "Marquette",
      "Sales": 171.03
  },
  {
      "County": "Menominee",
      "Sales": 48.65
  },
  {
      "County": "Milwaukee",
      "Sales": 10774.19
  },
  {
      "County": "Monroe",
      "Sales": 510.50
  },
  {
      "County": "Oconto",
      "Sales": 420.57
  },
  {
      "County": "Oneida",
      "Sales": 402.21
  },
  {
      "County": "Outagamie",
      "Sales": 2032.45
  },
  {
      "County": "Ozaukee",
      "Sales": 981.08
  },
  {
      "County": "Pepin",
      "Sales": 82.95
  },
  {
      "County": "Pierce",
      "Sales": 461.79
  },
  {
      "County": "Polk",
      "Sales": 489.97
  },
  {
      "County": "Portage",
      "Sales": 793.17
  },
  {
      "County": "Price",
      "Sales": 155.55
  },
  {
      "County": "Racine",
      "Sales": 2198.07
  },
  {
      "County": "Richland",
      "Sales": 199.67
  },
  {
      "County": "Rock",
      "Sales": 1811.50
  },
  {
      "County": "Rusk",
      "Sales": 162.23
  },
  {
      "County": "St. Croix",
      "Sales": 968.41
  },
  {
      "County": "Sauk",
      "Sales": 711.82
  },
  {
      "County": "Sawyer",
      "Sales": 186.10
  },
  {
      "County": "Shawano",
      "Sales": 469.31
  },
  {
      "County": "Sheboygan",
      "Sales": 1295.15
  },
  {
      "County": "Taylor",
      "Sales": 232.27
  },
  {
      "County": "Trempealeau",
      "Sales": 333.38
  },
  {
      "County": "Vernon",
      "Sales": 341.80
  },
  {
      "County": "Vilas",
      "Sales": 240.81
  },
  {
      "County": "Walworth",
      "Sales": 1160.17
  },
  {
      "County": "Washburn",
      "Sales": 176.78
  },
  {
      "County": "Washington",
      "Sales": 1495.94
  },
  {
      "County": "Waukesha",
      "Sales": 4438.53
  },
  {
      "County": "Waupaca",
      "Sales": 589.24
  },
  {
      "County": "Waushara",
      "Sales": 274.18
  },
  {
      "County": "Winnebago",
      "Sales": 1910.69
  },
  {
      "County": "Wood",
      "Sales": 833.50
  }
        ];
    }

    if (state == 'WV') {
        items = [
  {
      "County": "Barbour",
      "Sales": 148.37
  },
  {
      "County": "Berkeley",
      "Sales": 961.76
  },
  {
      "County": "Boone",
      "Sales": 214.32
  },
  {
      "County": "Braxton",
      "Sales": 128.30
  },
  {
      "County": "Brooke",
      "Sales": 210.01
  },
  {
      "County": "Cabell",
      "Sales": 859.37
  },
  {
      "County": "Calhoun",
      "Sales": 66.92
  },
  {
      "County": "Clay",
      "Sales": 81.78
  },
  {
      "County": "Doddridge",
      "Sales": 73.82
  },
  {
      "County": "Fayette",
      "Sales": 403.43
  },
  {
      "County": "Gilmer",
      "Sales": 76.72
  },
  {
      "County": "Grant",
      "Sales": 104.04
  },
  {
      "County": "Greenbrier",
      "Sales": 315.35
  },
  {
      "County": "Hampshire",
      "Sales": 207.43
  },
  {
      "County": "Hancock",
      "Sales": 267.99
  },
  {
      "County": "Hardy",
      "Sales": 123.15
  },
  {
      "County": "Harrison",
      "Sales": 610.22
  },
  {
      "County": "Jackson",
      "Sales": 258.15
  },
  {
      "County": "Jefferson",
      "Sales": 487.25
  },
  {
      "County": "Kanawha",
      "Sales": 1692.27
  },
  {
      "County": "Lewis",
      "Sales": 145.56
  },
  {
      "County": "Lincoln",
      "Sales": 190.74
  },
  {
      "County": "Logan",
      "Sales": 318.39
  },
  {
      "County": "McDowell",
      "Sales": 184.70
  },
  {
      "County": "Marion",
      "Sales": 503.13
  },
  {
      "County": "Marshall",
      "Sales": 287.18
  },
  {
      "County": "Mason",
      "Sales": 239.99
  },
  {
      "County": "Mercer",
      "Sales": 548.39
  },
  {
      "County": "Mineral",
      "Sales": 245.11
  },
  {
      "County": "Mingo",
      "Sales": 229.15
  },
  {
      "County": "Monongalia",
      "Sales": 904.85
  },
  {
      "County": "Monroe",
      "Sales": 119.29
  },
  {
      "County": "Morgan",
      "Sales": 154.81
  },
  {
      "County": "Nicholas",
      "Sales": 229.72
  },
  {
      "County": "Ohio",
      "Sales": 386.87
  },
  {
      "County": "Pendleton",
      "Sales": 66.10
  },
  {
      "County": "Pleasants",
      "Sales": 67.04
  },
  {
      "County": "Pocahontas",
      "Sales": 76.70
  },
  {
      "County": "Preston",
      "Sales": 299.56
  },
  {
      "County": "Putnam",
      "Sales": 501.20
  },
  {
      "County": "Raleigh",
      "Sales": 697.46
  },
  {
      "County": "Randolph",
      "Sales": 260.24
  },
  {
      "County": "Ritchie",
      "Sales": 89.12
  },
  {
      "County": "Roane",
      "Sales": 129.67
  },
  {
      "County": "Summers",
      "Sales": 120.00
  },
  {
      "County": "Taylor",
      "Sales": 150.17
  },
  {
      "County": "Tucker",
      "Sales": 61.65
  },
  {
      "County": "Tyler",
      "Sales": 79.58
  },
  {
      "County": "Upshur",
      "Sales": 218.22
  },
  {
      "County": "Wayne",
      "Sales": 366.61
  },
  {
      "County": "Webster",
      "Sales": 78.68
  },
  {
      "County": "Wetzel",
      "Sales": 143.36
  },
  {
      "County": "Wirt",
      "Sales": 52.21
  },
  {
      "County": "Wood",
      "Sales": 765.90
  },
  {
      "County": "Wyoming",
      "Sales": 203.66
  }
        ];
    }

    if (state == 'WY') {
        items = [
  {
      "County": "Albany",
      "Sales": 84.11
  },
  {
      "County": "Big Horn",
      "Sales": 26.96
  },
  {
      "County": "Campbell",
      "Sales": 108.28
  },
  {
      "County": "Carbon",
      "Sales": 35.40
  },
  {
      "County": "Converse",
      "Sales": 32.17
  },
  {
      "County": "Crook",
      "Sales": 16.15
  },
  {
      "County": "Fremont",
      "Sales": 92.15
  },
  {
      "County": "Goshen",
      "Sales": 30.59
  },
  {
      "County": "Hot Springs",
      "Sales": 10.89
  },
  {
      "County": "Johnson",
      "Sales": 19.39
  },
  {
      "County": "Laramie",
      "Sales": 215.34
  },
  {
      "County": "Lincoln",
      "Sales": 41.28
  },
  {
      "County": "Natrona",
      "Sales": 182.00
  },
  {
      "County": "Niobrara",
      "Sales": 5.71
  },
  {
      "County": "Park",
      "Sales": 65.69
  },
  {
      "County": "Platte",
      "Sales": 19.70
  },
  {
      "County": "Sheridan",
      "Sales": 67.03
  },
  {
      "County": "Sublette",
      "Sales": 22.57
  },
  {
      "County": "Sweetwater",
      "Sales": 101.68
  },
  {
      "County": "Teton",
      "Sales": 50.05
  },
  {
      "County": "Uinta",
      "Sales": 47.35
  },
  {
      "County": "Washakie",
      "Sales": 19.02
  },
  {
      "County": "Weston",
      "Sales": 16.09
  }
        ];
    }

    chart.setDataSource(items);
}
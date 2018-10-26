(function(){

    var filteredCfxItems = {};

    function processItems(items)
    {
        var filteredItems = items.filter(function (item) {
            var name = item.name;

            // Remove obfuscated classes as they are not meant to be created by name
            if (name.length <= 2)
                return false;

            // Remove functions from object
            if ((item.value == null) || !item.value._isCFXNamespace) {
                var c = name.charAt(0);
                if (c != c.toUpperCase())
                    return false;
            }

            return true;
        });

        filteredItems.forEach(function (item) {
            if (item.value) {
                if (item.value._isCFXEnum)
                    item.glyph = 'vs:GlyphGroupEnum';
                else if (item.value._isCFXNamespace)
                    item.glyph = 'vs:GlyphGroupNamespace';
            }
        });

        return filteredItems;
    }

    intellisense.addEventListener('statementcompletion', function (event) {
        var targetName = event.targetName;
        if ((targetName == "cfx") || ((event.target != null) && (event.target._isCFXNamespace))) {
            var filteredItems = filteredCfxItems[targetName];
            if (!filteredItems) {
                filteredItems = processItems(event.items);
                filteredCfxItems[targetName] = filteredItems;
            }

            event.items = filteredItems;
        } else {
            if ((event.target != null) && (event.target._isCFXEnum)) {
                event.items = event.items.filter(function (item) {
                    var c = item.name.charAt(0);
                    return c == c.toUpperCase();
                });
            }
        }
    });

})();

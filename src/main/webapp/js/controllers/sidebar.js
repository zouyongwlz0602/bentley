define(['./module', 'jquery', 'ace'], function(module, jQuery) {
    module.controller('SidebarCtrl', function() {
        ace.handle_side_menu(jQuery);
    });
});
(function () {
  'use strict';
  angular.module('BlurAdmin.theme').factory('notification', notification);      
  function notification(toastr){
  	return {
  		'notify':notify
  	}

  	function notify(title, message){
  		toastr.success(message, title, {
          "autoDismiss": true,
          "positionClass": "toast-bottom-right",
          "type": "success",
          "timeOut": "5000",
          "extendedTimeOut": "2000",
          "allowHtml": true,
          "closeButton": false,
          "tapToDismiss": true,
          "progressBar": false,
          "newestOnTop": true,
          "maxOpened": 0,
          "preventDuplicates": false,
          "preventOpenDuplicates": false
        });
  	}
  }
})();
(function () {
  'use strict';

  angular.module('BlurAdmin.theme').factory('oauthService', oauthService);      
  function oauthService(config){
  	return {
  		'saveAndRecheckToken': saveAndRecheckToken,
  		'decodeToken' : decodeToken,
  		'getToken': getToken,
      'recheckToken' : recheckToken
  	};

  	function saveAndRecheckToken(){
  		var date = new Date();
    	var storage = window.localStorage.getItem('slerp');
    	if(storage == undefined || storage == null || storage == ''){
    		window.location.href = config.OAUTH_URL + '/oauth/authorize?client_id=' + config.CLIENT_ID+ '&response_type=token&state='+ date.getTime() + '&redirect_uri=http://slerp.com:3000/redirect.html';
    	}
  	}

    function recheckToken(){
      var date = new Date();
      window.location.href = config.OAUTH_URL + '/oauth/authorize?client_id=' + config.CLIENT_ID+ '&response_type=token&state='+ date.getTime() + '&redirect_uri=http://slerp.com:3000/redirect.html';
    }

  	function decodeToken(){
      var token = getToken();  		
      if(token == null)
        return null;

      token = getToken().access_token;

      var base64Url = token.split('.')[1];
      var base64 = base64Url.replace('-', '+').replace('_', '/');
      return angular.fromJson(window.atob(base64));
  	}

  	function getToken(){
      if(window.localStorage.getItem('slerp') !== null){
  		  var oauth = angular.fromJson(window.localStorage.getItem('slerp')).oauth;
        return oauth != null ? oauth : undefined;
      }else{
        recheckToken();
      }
  		return null;
  	}
  }
})();

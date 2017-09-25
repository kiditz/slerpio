(function () {
  'use strict';

  angular.module('BlurAdmin.pages.profile').factory('profileService', profileService);      
  function profileService($http, $q, config, oauthService){
    return {
      getProfile : getProfile,
      updateProfile: updateProfile,
      updateImage: updateImage,
      getImage: getImage

    }
    function getImage(){      
      var deferred = $q.defer();      
      var token = oauthService.getToken().access_token;
      //console.log(token);
      var username = oauthService.decodeToken().user_name;
      $http.get(config.SLERPIO + '/image?username=' + username, {headers: {
        'Authorization' : 'Bearer ' + token
      }, responseType: "blob"}).then(function(resp){        
        deferred.resolve(resp.data);
      }, function(err){       
        if(err.status == 401) 
          oauthService.recheckToken();
        deferred.reject(err);
      })
      return deferred.promise;
    }
    function getProfile(){      
      var deferred = $q.defer();      
      var token = oauthService.getToken().access_token;
      //console.log(token);
      var username = oauthService.decodeToken().user_name;
      $http.get(config.SLERPIO + '/profile?username=' + username, {headers: {
        'Authorization' : 'Bearer ' + token
      }}).then(function(resp){
        console.log(resp.data);
        deferred.resolve(resp.data);
      }, function(err){       
        if(err.status == 401) 
          oauthService.recheckToken();
        deferred.reject(err);
      })
      return deferred.promise;
    }

    function updateProfile(profile){
      var token = oauthService.getToken().access_token;
      var oldUsername = oauthService.decodeToken().user_name;      
      var data = {
        'oldUsername': oldUsername, 
        'newUsername': profile.username,        
        'email':profile.email,
        'phoneNumber':profile.phoneNumber,
        'fullname':profile.fullname,
        'imagePath':profile.imagePath,
        'address':profile.address
      }
      
      var deferred = $q.defer();      
      
      $http.put(config.SLERPIO + '/profile', data , {headers: {
        'Authorization' : 'Bearer ' + token,
        'Content-Type': 'application/json'
      }}).then(function(resp){
        deferred.resolve(resp.data);
      }, function(err){       
        console.log(err)
        if(err.status == 401) 
          oauthService.recheckToken();
      })
      return deferred.promise;
    }
    function updateImage(picture){      
      var deferred = $q.defer();
      var token = oauthService.getToken().access_token;
      var username = oauthService.decodeToken().user_name;
      var data = {'picture': picture, 'username': username};
      //console.log(picture);
      $http.post(config.SLERPIO + '/photo_profile', data , {headers: {
        'Authorization' : 'Bearer ' + token,
        'Content-Type': 'application/json'
      }}).then(function(resp){
        deferred.resolve(resp.data);
      }, function(err){       
        console.log(err)
        if(err.status == 401) 
          oauthService.recheckToken();
      })
      return deferred.promise;
    }
  }      

})();

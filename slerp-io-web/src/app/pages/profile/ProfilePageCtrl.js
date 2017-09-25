/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.profile')
    .controller('ProfilePageCtrl', ProfilePageCtrl);

  /** @ngInject */
  function ProfilePageCtrl($scope, fileReader, $filter, $uibModal, profileService, notification) {
    $scope.picture = $filter('profilePicture')('Nasta');
    var vm = this;
    vm.profile = {};
    vm.image = {};
    init();        
    
    function init(){
      profileService.getProfile().then(function(data){
          vm.profile = data.profile;
      });
      profileService.getImage().then(function(data){
        $scope.getFile(data);
      });
    }
    
    vm.updateProfile = function(profile){      
      profileService.updateProfile(profile).then(function(response){
        notification.notify('Edit Profile', 'Your profile has been updated');
        init();
      });
    }
   
    $scope.removePicture = function () {
      $scope.picture = $filter('appImage')('theme/no-photo.png');
      $scope.noPicture = true;
    };

    $scope.uploadPicture = function () {
      var fileInput = document.getElementById('uploadFile');
      fileInput.click();
    };

    $scope.socialProfiles = [
      {
        name: 'Facebook',
        href: 'https://www.facebook.com/akveo/',
        icon: 'socicon-facebook'
      },
      {
        name: 'Twitter',
        href: 'https://twitter.com/akveo_inc',
        icon: 'socicon-twitter'
      },
      {
        name: 'Google',
        icon: 'socicon-google'
      },
      {
        name: 'LinkedIn',
        href: 'https://www.linkedin.com/company/akveo',
        icon: 'socicon-linkedin'
      },
      {
        name: 'GitHub',
        href: 'https://github.com/akveo',
        icon: 'socicon-github'
      },
      {
        name: 'StackOverflow',
        icon: 'socicon-stackoverflow'
      },
      {
        name: 'Dribbble',
        icon: 'socicon-dribble'
      },
      {
        name: 'Behance',
        icon: 'socicon-behace'
      }
    ];

    $scope.unconnect = function (item) {
      item.href = undefined;
    };

    $scope.showModal = function (item) {
      $uibModal.open({
        animation: false,
        controller: 'ProfileModalCtrl',
        templateUrl: 'app/pages/profile/profileModal.html'
      }).result.then(function (link) {
          item.href = link;
        });
    };
    $scope.onFileSelect = function(file){
        if(file !== undefined && file instanceof Blob){
          $scope.getAndUpdateFile(file);
        }
    }
    $scope.getFile = function (file) {      
      fileReader.readAsDataUrl(file, $scope).then(function (result) {
        $scope.picture = result;                                                
        
      });
    };
    $scope.getAndUpdateFile = function (file) {      
      fileReader.readAsDataUrl(file, $scope).then(function (result) {
        $scope.picture = result;                                                
        profileService.updateImage($scope.picture).then(function(resp){            
        });
      });
    };
    $scope.switches = [true, true, false, true, true, false];
  }

})();

/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.profile')
    .controller('ProfileModalCtrl', ProfileModalCtrl);

  /** @ngInject */
  function ProfileModalCtrl($scope, $uibModalInstance) {
    console.log($scope.userLogin)
    $scope.link = '';
    $scope.ok = function () {      
      $uibModalInstance.close($scope.link);
    };
  }

})();
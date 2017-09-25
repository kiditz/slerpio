/**
 * @author kiditz
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.activity', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
     .state('activity', {
          url: '/activity',
          title: 'Aktivitas',
          templateUrl: 'app/pages/activity/activity.html',
          controller: 'activityCtrl',
          sidebarMeta: {
            icon: 'ion-calendar',
            order: 100,
          },
        });
  }
})();
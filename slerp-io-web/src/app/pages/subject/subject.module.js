/**
 * @author kiditz
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.subject', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
     .state('subject', {
          url: '/subject',
          title: 'Materi Sekolah',
          templateUrl: 'app/pages/subject/subject.html',          
          sidebarMeta: {
            icon: 'ion-easel',
            order: 100,
          },
        });
  }
})();
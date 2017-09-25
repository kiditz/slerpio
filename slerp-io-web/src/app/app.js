'use strict';

angular.module('BlurAdmin', [
  'ngAnimate',
  'ui.bootstrap',
  'ui.sortable',
  'ui.router',
  'ngTouch',
  'toastr',
  'smart-table',
  "xeditable",
  'ui.slimscroll',
  'ngJsTree',
  'ngFileUpload',
  'angular-progress-button-styles',
  'BlurAdmin.theme',
  'BlurAdmin.pages'
]).constant('config', {
  CLIENT_ID : 'kiditz',
  OAUTH_URL : 'http://172.17.0.1:8019',
  SLERPIO : 'http://172.17.0.1:8020',
});
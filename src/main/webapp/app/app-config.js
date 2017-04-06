// Define the `noteApp` module
angular.module('noteApp')
    .config(['$locationProvider', '$routeProvider',
             function config($locationProvider, $routeProvider) {
                 $routeProvider.when('/notes', {
                     template: '<note-list></note-list>'
                 }).when('/register', {
                     template: '<register></register>'
                 }).when('/login', {
                     template: '<login></login>'
                 }).when('/profile', {
                     template: '<profile-page></profile-page>'
                 }).otherwise('/notes');
             }]);
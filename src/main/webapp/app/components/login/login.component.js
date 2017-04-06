angular.module('noteApp').component('login', {
    templateUrl: 'components/login/login.template.html',
    controller: ['$http', '$cookies', '$location', function LoginController($http, $cookies, $location) {
        var self = this;

        self.loginSubmit = loginSubmit;
        self.redirectToRegister = redirectToRegister;

        function loginSubmit(username, password) {
            $http({
                      url: 'http://localhost:8080/simpletodo/login',
                      method: "POST",
                      data: {
                          "username": username,
                          "password": password
                      }
                  })
                .then(function success(response) {
                    responseData = response.data;
                    $cookies.put('userId', responseData.loginedUserId);
                    $location.path("/notes")
                }, function error(response) {
                    self.errorMessage = response.data.msg;
                })
        }

        function redirectToRegister() {
            $location.path("/register");
        }
    }]
});
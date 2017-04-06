angular.module('noteApp').component('register', {
    templateUrl: 'components/register/register.template.html',
    controller: ['$http', '$cookies', '$location', function RegisterController($http, $cookies, $location) {
        var self = this;

        self.registerUser = registerUser;

        function registerUser(username, password) {
            console.log(username);
            console.log(password)

            $http({
                      url: 'http://localhost:8080/simpletodo/user',
                      method: "POST",
                      data: {
                          "username": username,
                          "password": password,
                          "enabled": "true"
                      }
                  })
                .then(function success(response) {
                    console.log(response)
                    $cookies.put('userId', response.data.id);
                    $location.path("/notes")
                }, function error(response) {
                    console.log(response);
                    self.errorMessage = 'User with same username already exists!';
                })
        }
    }]
});
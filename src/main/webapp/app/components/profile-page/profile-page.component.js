angular.module('noteApp').component('profilePage', {
    templateUrl: 'components/profile-page/profile-page.template.html',
    controller: ['$http', '$cookies', '$location', function ProfileController($http, $cookies, $location) {
        var self = this;
        var storedUserId = $cookies.get('userId');
        var base_url = 'http://localhost:8080/simpletodo/user';
        self.isLoggined = false;

        self.updateUser = updateUser;
        self.logOut = logOut;

        if (storedUserId) {
            self.isLoggined = true;
        }

        if (self.isLoggined) {
            loadUserInfo();
        }

        function loadUserInfo() {
            console.log(storedUserId);
            if (!storedUserId) {
                $location.path("/login");
            }
            $http({
                      url: base_url + '?id=' + storedUserId,
                      method: "GET"
                  })
                .then(function success(response) {
                    console.log(response.data);
                    self.username = response.data.username;
                    self.password = response.data.password;
                    self.enabled = response.data.enabled;
                }, function error(response) {
                    console.log(response);
                })
        }

        function updateUser(username, password) {
            console.log(username);
            console.log(password)

            $http({
                      url: base_url,
                      method: "PUT",
                      data: {
                          "id": storedUserId,
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

        function logOut() {
            $cookies.remove('userId');
            $location.path("/login")
        }
    }]
});
angular.module('noteApp').component('noteList', {
    templateUrl: 'components/note-list/note-list.template.html',
    controller: ['$routeParams', '$http', '$cookies', '$location',
                 function NoteListController($routeParams, $http, $cookies, $location) {
                     var self = this;
                     var cookieUserId = $cookies.get('userId');
                     var base_url = 'http://localhost:8080/simpletodo/note';

                     self.selectedForEdit = true;

                     self.getNotesById = getNotesById;
                     self.updateNoteStatus = updateNoteStatus;
                     self.addNote = addNote;
                     self.selectNote = selectNote;
                     self.editNote = editNote;
                     self.deleteNote = deleteNote;
                     self.logOut = logOut;
                     self.redirectToLoginPage = redirectToLoginPage;
                     self.redirectUpdateProfile = redirectUpdateProfile;

                     self.isLoggined = false;
                     if (cookieUserId) {
                         self.isLoggined = true;
                     }

                     if (self.isLoggined) {
                         getNotesById(cookieUserId);
                     }
                     function getNotesById(userId) {
                         $http({
                                   url: base_url + '/all?id=' + userId,
                                   method: "GET"
                               })
                             .then(function success(response) {
                                 self.notes = response.data;
                             }, function error(response) {
                                 console.log(response);
                             })
                     }

                     function updateNoteStatus(id, done) {
                         console.log(id);
                         console.log(done);
                         console.log(cookieUserId);
                         $http({
                                   url: base_url,
                                   method: "PUT",
                                   data: {
                                       "id": id,
                                       "userId": cookieUserId,
                                       "done": done
                                   }
                               })
                             .then(function success(response) {
                                 console.log(response)
                             }, function error(response) {
                                 console.log(response);
                             })
                     }

                     function addNote() {
                         console.log(self.noteText);
                         $http({
                                   url: base_url,
                                   method: "POST",
                                   data: {
                                       "userId": cookieUserId,
                                       "noteMessage": self.noteText,
                                       "done": false
                                   }
                               })
                             .then(function success(response) {
                                 console.log(response)
                                 self.notes.push(response.data);
                                 self.noteText = '';
                             }, function error(response) {
                                 console.log(response);
                                 self.noteText = '';
                             })
                     }

                     function selectNote(id, message, index) {
                         console.log(id + message);
                         console.log(self.selectedForEdit);
                         console.log(index);
                         self.selectedForEdit = false;
                         self.updateNoteId = id;
                         self.updateNoteMessage = message;
                         self.updateIndex = index;
                     }

                     function editNote() {
                         self.selectedForEdit = true;
                         $http({
                                   url: base_url,
                                   method: "PUT",
                                   data: {
                                       "id": self.updateNoteId,
                                       "userId": cookieUserId,
                                       "noteMessage": self.updateNoteMessage
                                   }
                               })
                             .then(function success(response) {
                                 console.log(response);
                                 self.notes.splice(self.updateIndex, 1);
                                 self.notes.splice(self.updateIndex, 0, response.data);
                             }, function error(response) {
                                 console.log(response);
                             })
                     }

                     function deleteNote(id, index) {
                         console.log(id);
                         console.log(index);
                         $http({
                                   url: base_url + '/' + id,
                                   method: "POST",
                               })
                             .then(function success(response) {
                                 console.log(response);
                                 self.notes.splice(index, 1);
                             }, function error(response) {
                                 console.log(response);
                             })
                     }

                     function logOut() {
                         $cookies.remove('userId');
                         $location.path("/login")
                     }

                     function redirectToLoginPage() {
                         $location.path("/login")
                     }

                     function redirectUpdateProfile() {
                         $location.path("/profile")
                     }
                 }]
});
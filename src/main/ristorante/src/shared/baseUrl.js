//this is a configuration file where we will setup whatever we require for server communication

//this is the single place where we can update the server address and use this const elsewhere in our app
//export const baseUrl = 'http://localhost:3001/';
let url = (location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: ''));
console.log(url);
console.log(url);
console.log(url);
console.log(url);
export const baseUrl = url +'/';
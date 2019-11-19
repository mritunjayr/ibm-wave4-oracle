// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  savedData:'http://localhost:8081/api/v1/saveUser',
  loginData:'http://localhost:8081/api/v1/login',
  allUserData:'http://localhost:8081/api/v1/users',
  singleUserData:'http://localhost:8081/api/v1/user',
  manaul_answer:'http://localhost:8091/api/v1/',
  chatHistoryService :"http://localhost:9000/api/v1",
  botSocket:'http://localhost:8090/socket',
  botService:'http://localhost:8090/api/v1',
  dialogflow:{
    angularBot:'aa0e2a8f0792403c8119d2410e7aafd1'
  },
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.

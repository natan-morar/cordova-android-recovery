exports.save = function (key, value) {
  return new Promise(function (resolve, reject) {
    cordova.exec(resolve, reject, "AndroidRecovery", "save", [key, value]);
  });
};

exports.recover = function (key) {
  return new Promise(function (resolve, reject) {
    function onSucces(result) {
      resolve(result.value);
    }
    cordova.exec(onSucces, reject, "AndroidRecovery", "recover", [key]);
  });
};

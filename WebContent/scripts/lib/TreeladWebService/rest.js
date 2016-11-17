var service = {};
service.rest = treelad.Class.create({

  init: function(cfg) {
    treelad.Class.extend(this, treelad.Class.extend({
      xhttp: new XMLHttpRequest()
    }, cfg));
  },

  prepareHeaders: function(headers) {
    var t = this;
    t.xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    var cookieManager = new service.cookieManager({});
    var userId = cookieManager.readCookie("userId");
    var token = cookieManager.readCookie("token");
    if (userId && token) {
      t.xhttp.setRequestHeader("user-id", userId);
      t.xhttp.setRequestHeader("x-access-token", token);
    }
    for (var index in headers) {
      t.xhttp.setRequestHeader(headers[index].type, headers[index].value);
    }
  },

  success: function() {
    var t = this;
    if (t.onSuccess) {
      t.onSuccess(JSON.parse(t.xhttp.responseText));
    }
  },

  failure: function() {
    var t = this;
    if (t.onFailure) {
      t.onFailure(t.xhttp.responseText);
    }
  },

  checkState: function() {
    var t = this;
    if (t.xhttp.readyState == 4 && t.xhttp.status == 200) {
      t.success();
    } else if (t.xhttp.readyState == 4) {
      t.failure();
    }
  },

  invokeGET: function(uri, headers, body) {
    var t = this;
    t.xhttp.onreadystatechange = function() {
      t.checkState();
    }
    t.xhttp.open("GET", uri, true);
    t.prepareHeaders(headers);
    t.xhttp.send();
  },

  invokePOST: function(uri, headers, body) {
    var t = this;
    t.xhttp.onreadystatechange = function() {
      t.checkState();
    }
    t.xhttp.open("POST", uri, true);
    t.prepareHeaders();
    t.xhttp.send(JSON.stringify(body));
  },

  invokePUT: function(uri, headers, body) {

  },

  invokeDELETE: function(uri, headers, body) {

  },

});

service.cookieManager = treelad.Class.create({

  init: function(cfg) {
    treelad.Class.extend(this, treelad.Class.extend({
    }, cfg));
  },
  //source - http://www.quirksmode.org/js/cookies.html

  createCookie: function(name, value, days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
  },

  readCookie: function(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
  },

  eraseCookie: function(name) {
    createCookie(name,"",-1);
  }

});

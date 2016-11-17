treelad.ContentViewer = treelad.Class.create({

  init: function(cfg) {
    treelad.Class.extend(this, treelad.Class.extend({
    }, cfg));
  },

  render: function() {
    var t = this;
    t.root = $_element({
      $_tag: "div",
      $_append: [{
        $_tag: "p",
        id: "pId",
        $_append: "cos"
      }, {
        $_tag: "input",
        id: "inputNameId",
        $_append: ""
      }, {
        $_tag: "button",
        $_append: "Test",
        $_on: {
          click: function() {
            var heroName = t.root.find("inputNameId").dom().value;
            var rest = new service.rest({
              onSuccess: function(model) {
                var rest = new service.rest({
                  onSuccess: function(model) {
                    t.userGrid.fill({
                      rows: model.heroes
                    })
                  },
                  onFailure: function(model) {
                    console.log(model);
                  }
                });
                rest.invokeGET("http://localhost:8080/EJBServer/eds/heroes/", [], {})
              },
              onFailure: function(model) {
                console.log(model);
              }
            });
            if (heroName) {
              rest.invokePOST("http://localhost:8080/EJBServer/eds/heroes/", [], {name: heroName})
            } else {
              console.log("Brak imienia");
            }
          }
        }
      }, {
        $_tag: "button",
        $_append: "Logout",
        $_on: {
          click: function() {
            t.root.find("inputNameId").dom().value = "asd";
            t.root.find("pId").update("Tekst zmieniony");
            //console.log(t.root.find("inputId").dom().value);
            //console.log(document.cookie);
            //$HEADER_USER_TOKEN = {};
          }
        }
      }]

    });

    t.userGrid = new treelad.dataTable.DataTable({
     inside: t.inside,
     columns: [{
       label: "Imie",
       property: "name"
     }]
   });
   var rest = new service.rest({
     onSuccess: function(model) {
       t.userGrid.fill({
         rows: model.heroes
       })
     },
     onFailure: function(model) {
       console.log(model);
     }
   });
   rest.invokeGET("http://localhost:8080/EJBServer/eds/heroes/", [], {})
   t.inside.insert(t.root);
    //t.inside.insert(t.userGrid);
  }

});

$MAIN = new treelad.ContentViewer({
  inside: $_element(document.body),
});

$MAIN.render();

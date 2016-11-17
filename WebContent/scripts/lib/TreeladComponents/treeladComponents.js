treelad.dataTable = {};

treelad.dataTable.DataTable = treelad.Class.create({

  init: function(cfg) {
    treelad.Class.extend(this, treelad.Class.extend({
      id: "#" + (new Date().getTime()),
    }, cfg));
    this.render();
  },

  render: function() {
    var t = this;
    t.root = $_element({
      $_tag: "div", class: "row",
      $_append: [{
        $_tag: "div", class: "col-md-12",
        $_append: [{
          $_tag: "div", class: "table-responsive",
          $_append: [{
            $_tag: "table", id: "my-table", class: "table table-bordred table-striped",
            $_append: [{
              $_tag: "thead",
              $_append: t.columns.map(function(column, index) {
                return {
                  $_tag: "th",
                  $_append: column.label
                };
              })
            }, {
              $_tag: "tbody", id: "row",
            }]
          }]
        }]
      }]

    });
    t.inside.update(t.root);
  },

  fill: function(model) {
    var t = this;
    if (t.model) {
      delete t.model;
      t.root.find("row").update();
    }
    t.model = model;
    t.model.rows.forEach(function(row) {
      var record = $_element({
        $_tag: "tr"
      });
      var keys = Object.keys(row);
      for (var i = 0; i < keys.length; i++) {
        var renderElement = null;
        if (t.columns.filter(function(e) {
          if (typeof e.render == 'function' && e.property == keys[i]) {
            renderElement = e.render();
            return true;
          } else {
            return e.property == keys[i];
          }
        }).length > 0) {
          if (renderElement) {
            record.insert($_element({$_tag: "td", $_append: [renderElement.domNode]}));
          } else {
            record.insert($_element({$_tag: "td", $_append: row[keys[i]]}));
          }
        }
      }
      t.root.find("row").insert(record);
    })
},

id: function(name) {
  return this.id + name;
},

});

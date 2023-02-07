let width = 800; //window.innerWidth;
let height = 400; //window.innerHeight;
let svg = d3
  .select("#treeArea")
  .append("svg")
  .attr("width", width)
  .attr("height", height);
let gContainer = svg.append("g").attr("transform", "translate(80,50)");

// Load data, currently a json file from github

// We need a PHP script to grab the correct data (treeId), rather than a JSON file
// d3.json(get_tree.php).then( ... );
// Another PHP script will grab individual Person information (id)
// d3.json(get_person.php).then( ... );
d3.json("https://raw.githubusercontent.com/gvalencia4/D3/main/Family%20Tree/test-data.json")
  .then(function (data) {
    // If data is fetched, draw the tree svg
    buildTree(data);
    })
  .catch(function(error) {
    // Do some error handling
    console.log("Error in data request.");
  });

  // Build tree
  function buildTree(data) {
    console.log("Data:");
            console.log(data);

            // Stratify data
            let dataStructure = d3
              .stratify()
              .id(function (d) {
                return d.child;
              })
              .parentId(function (d) {
                return d.parent;
              })(data);

            // Define the tree structure
            let treeStructure = d3
              .tree()
              .separation(function (a, b) {
                return a.parent === b.parent ? 2 : 2;
              })
              .size([650, 300]);

            // Create the x,y tree structure (links and descendants)
            let information = treeStructure(dataStructure);

            console.log("information")
            console.log(information);
            console.log("descendants")
            console.log(information.descendants());
            console.log("links")
            console.log(information.links());


            // For quickly adjusting person cards in the x
            let personCardLocation = 0;

            // Container of Family Tree
            var rectangleOutline = svg
              .append("rect")
              .attr("id", "toZoom")
              .attr("width", width)
              .attr("height", height);

            // Elbow Connectors
            let connections = gContainer
              .append("g")
              .selectAll("path")
              .data(information.links());
            connections
              .enter()
              .append("path")
              .attr("d", function (d) {
                return (
                  "M" +
                  (d.source.x - personCardLocation) +
                  "," +
                  d.source.y +
                  " v 50 H" +
                  d.target.x +
                  " V" +
                  d.target.y
                );
              });

            // Rectangles will become Person cards (Name, age, picture, etc.)
            let rectangles = gContainer
              .append("g")
              .selectAll("rect")
              .data(information.descendants());
            rectangles
              .enter()
              .append("rect")
              .classed("shape", true)
              .attr("x", function (d) {
                return d.x - 60 - personCardLocation;
              })
              .attr("y", function (d) {
                return d.y - 20;
              }); //or y - x/3.236

            // Spouses
            // let spouseRectangles = gContainer.append("g").selectAll("rect").data(information.descendants());
            // spouseRectangles.enter().append("rect")
            //   .attr("x", function(d){return d.x + 60 - personCardLocation})
            //   .attr("y", function(d){return d.y - 20}) //or y - x/3.236
            //   .classed("hide", function (d) {
            //     if(d.data.spouse == undefined)
            //       return true;
            //     else
            //       return false;
            //   });

            // Pictures
            let pictures = gContainer
              .append("g")
              .selectAll("image")
              .data(information.descendants());
            pictures
              .enter()
              .append("image")
              .attr("href", function (d) {
                return d.data.icon;
              })
              .attr("x", function (d) {
                return d.x - 65 - personCardLocation;
              })
              .attr("y", function (d) {
                return d.y - 17;
              });

            // Name text
            let names = gContainer
              .append("g")
              .selectAll("text")
              .data(information.descendants());
            names
              .enter()
              .append("text")
              .text(function (d) {
                return d.data.child;
              })
              .attr("x", function (d) {
                return d.x + 20 - personCardLocation;
              })
              .attr("y", function (d) {
                return d.y + 5;
              });

  }

// Zoom
let zoom = d3.zoom().scaleExtent([0.25, 5]).on("zoom", handleZoom);

function initZoom() {
  d3.select("svg").call(zoom);
}

function handleZoom(e) {
  d3.select("svg g").attr("transform", e.transform);
}

function zoomIn() {
  d3.select("svg").transition().call(zoom.scaleBy, 2);
}

function zoomOut() {
  d3.select("svg").transition().call(zoom.scaleBy, 0.5);
}

function resetZoom() {
  d3.select("svg").transition().call(zoom.scaleTo, 1);
}

function center() {
  d3.select("svg")
    .transition()
    .call(zoom.translateTo, 0.5 * width - 80, 0.5 * height - 50);
}

// Places the tree without a transition
// Note the manual width and height adjustment of 80, 50
function centerStart() {
  d3.select("svg").call(zoom.translateTo, 0.5 * width - 80, 0.5 * height - 50);
}

function panLeft() {
  d3.select("svg").transition().call(zoom.translateBy, -50, 0);
}

function panRight() {
  d3.select("svg").transition().call(zoom.translateBy, 50, 0);
}

initZoom();
centerStart();

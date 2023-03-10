// Container for tree
const viewBoxWidth = 1000;
const viewBoxHeight = 500;

const treeStartLocation = {
//    x: 0.5 * viewBoxWidth - 80,
//    y: 0.5 * viewBoxHeight - 50,
    x: 0,
    y: 435,
    scale: .55,
}

let svg = d3
  .select("#treeArea")
  .append("svg")
  .attr("width", viewBoxWidth)
  .attr("height", viewBoxHeight);

// gContainer contains all the elements that make up the tree
let gContainer = svg.append("g"); //.attr("transform", "translate(500,50)");

// Container of Family Tree
let viewBox = svg
   .append("rect")
   .attr("id", "toZoom")
   .attr("width", viewBoxWidth)
   .attr("height", viewBoxHeight);

// Test data sets:
// https://raw.githubusercontent.com/gvalencia4/D3/main/Family%20Tree/
// test-data-fifteen-person.json
// test-data-thirteen-person.json
// test-data-eleven-person.json *
// test-data-four-person.json

d3.json(
  "http://localhost:8080/tree/data/"
)
  .then(function (data) {
    // If data is fetched, draw the tree svg
    buildTree(data);
  })
  .catch(function (error) {
    // Do some error handling
    let treeDataError = svg;
    let treeErrorMessageString = "";
    // If there is no root, the user is asked to add people to the tree
    if (error == 'Error: no root') {
        treeErrorMessageString = "Add people to your database so they show up here!";
        treeDataError
            .append("a")
             .attr("href", function (d) {
                return "/person/add/";
             })
             .attr("x", function (d) {
                return "50%";
             })
             .attr("y", function (d) {
                return "50%";
             })
             .append("text")
             .text(treeErrorMessageString)
             .attr("x", function (d) {
                return "50%";
             })
             .attr("y", function (d) {
                return "50%";
             });
    } else {
        treeErrorMessageString = "Issue requesting tree information (" + error + ")";

        treeDataError
            .append("text")
            .text(treeErrorMessageString)
            .attr("x", function (d) {
                return "50%";
            })
            .attr("y", function (d) {
                return "50%";
            });
    }

    console.error(error);
  });

// Build tree
function buildTree(data) {
    // Define the tree structure
    const personCardDimensions = {
        width: 279, // 275 + 4
        height: 114, // 110 + 4
        marginHeight: 279 * .5, // 279 * .5
        marginWidth: 50, //
    };

  // Stratify data
  let dataStructure = d3
    .stratify()
    .id(function (d) {
      return d.id;
    })
    .parentId(function (d) {
      return d.parentId;
    })(data);

  let treeStructure = d3
    .tree()
    .nodeSize([personCardDimensions.width, personCardDimensions.height + personCardDimensions.marginHeight])
    .separation(function (a, b) {
      return a.parent === b.parent ? 1.5 : 1.5;
    });

  // Create the x,y tree structure (links and descendants)
  let information = treeStructure(dataStructure);
  let treeHeight  = dataStructure.height * (personCardDimensions.height + personCardDimensions.marginHeight);

    // Elbow Connectors
    let treePaths = gContainer
        .append("g")
        .classed("pathGroup", true)
        .selectAll("path")
        .data(information.links());

      treePaths
        .enter()
        .append("path")
        .attr("d", function (d) {
          return (
            "M" +
            (d.source.x) +
            "," +
            (treeHeight - d.source.y) +
            " v -50 H" +
            d.target.x +
            " V" +
            (treeHeight - d.target.y)
          );
        });


  // Person cards
  let personCards = gContainer
    .append("g")
    .classed("rectangleGroup", true)
    .selectAll("a, rect")
    .data(information.descendants());

  // Outer border and link of person cards
  personCards
    .enter()
    .append("rect")
    .classed("personCard", true)
    .attr("x", function (d) {
      return d.x - (personCardDimensions.width/2);
    })
    .attr("y", function (d) {
      return treeHeight - d.y; //or y - x/3.236
    })
    .attr("rx","10px")
    .attr("ry","10px")
    .attr("stroke-linejoin","round");

    // Bootstrap person cards, with links
    personCards
      .enter()
      .append('foreignObject')
      .attr("x", function (d) {
      //console.log(d.data.birthday);
        return d.x - (personCardDimensions.width/2);
      })
      .attr("y", function (d) {
        return treeHeight - d.y + 5; // 5 centers content bootstrap inside personCards
      })
      .attr("width", "400")
      .attr("height", "100")
      .each(function(d) {
        d3.select(this).html(
                 `<a class="personTreeCardLink" href="/person/view/` + d.data.id + `">
                    <div class="row align-items-center g-0">
                         <div class="col-md-4">
                           <img src="https://github.com/Jan-23-Liftoff-KC/team-michael-group-repo/blob/main/src/main/resources/test-tree-data/person-icon.png?raw=true" class="rounded-start d-block ps-2 m" alt="..." style="width: 120px">
                         </div>
                         <div class="col-md-8">
                           <div class="card-body">
                             <h5 class="card-title">` + d.data.firstName + `<br>` + d.data.lastName + `</h5>
                             <p class="card-text"><small class="text-muted">Born: ` + d.data.birthday + `<br>Died: ` + d.data.deathday + `</small></p>
                           </div>
                         </div>
                    </div>
                  </a>
                  `)
       });
  }

// Spouses
// let spouseRectangles = gContainer.append("g").selectAll("rect").data(information.descendants());
// spouseRectangles.enter().append("rect")
//   .attr("x", function(d){return d.x + 60})
//   .attr("y", function(d){return d.y - 20}) //or y - x/3.236
//   .classed("hide", function (d) {
//     if(d.data.spouse == undefined)
//       return true;
//     else
//       return false;
//   });



// Zoom
let zoom = d3.zoom().scaleExtent([0.25, 2]).on("zoom", handleZoom);

// Called on page load
function initZoom() {
  d3.select("svg").call(zoom).call(zoom.scaleTo, treeStartLocation.scale);
}

function handleZoom(e) {
  d3.select("g").attr("transform", e.transform);
}

// Places the tree without transition
function centerStart() {
  d3.select("svg").call(zoom.translateTo, treeStartLocation.x, treeStartLocation.y);
}

function zoomIn() {
  d3.select("svg").transition().call(zoom.scaleBy, 2);
}

function zoomOut() {
  d3.select("svg").transition().call(zoom.scaleBy, 0.5);
}

function resetZoom() {
  d3.select("svg").transition().call(zoom.scaleTo, treeStartLocation.scale);
}

function center() {
  d3.select("svg")
    .transition()
    .call(zoom.translateTo, treeStartLocation.x, treeStartLocation.y);
}

function resetView() {
    d3.select("svg")
        .transition()
        .call(zoom.translateTo, treeStartLocation.x, treeStartLocation.y)
        .transition()
        .call(zoom.scaleTo, treeStartLocation.scale);

}

function panLeft() {
  d3.select("svg").transition().call(zoom.translateBy, -50, 0);
}

function panRight() {
  d3.select("svg").transition().call(zoom.translateBy, 50, 0);
}

function panUp() {
  d3.select("svg").transition().call(zoom.translateBy, 0, -50);
}

function panDown() {
  d3.select("svg").transition().call(zoom.translateBy, 0, 50);
}

initZoom();
centerStart();


final SpatialContext spatialCxt = SpatialContext.GEO;
final ShapeFactory shapeFactory = spatialCxt.getShapreFactory();
spatialCxt.getShapeFactory();
// Define how the spatial tree will be constructed — we choose recursively adding prefix here
with GeohashPrefixTree
// being the tree to be used with level depth 5. “Coordinates” is the field to be used as key.
final SpatialStrategy coordinatesStrategy = new RecursivePrefixTreeStrategy(new
GeohashPrefixTree(spatialCxt, 5), “coordinates”);
// Create an index
Final Directory directory = new RAMDirectory();
IndexWriterConfig iwConfig = new IndexWriterConfig();
IndexWriter indexWriter = new IndexWriter(directory, iwConfig);

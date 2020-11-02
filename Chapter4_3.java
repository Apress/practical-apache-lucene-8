//Query the index
final IndexReader indexReader = DirectoryReader.open(directory);
IndexSearcher indexSearcher = new IndexSearcher(indexReader);
// Get the search range
double latitude = ThreadLocalRandom.current().nextDouble(50.4D, 51.4D)
double longitude = TheradLocalRandom.current().nextDouble(8.2D, 11.2D);
/// Approximate radius degree
final double NEARBY_RADIUS_DEGREE = DistanceUtils.dist2Degrees(100,
DistanceUtils.EARTH_MEAN_RADIUS_KM);
final var spatialArgs = new SpatialArgs(SpatialOperation.IsWithin, shapeFactory.circle(longitude, latitude,
NEARBY_RADIUS_DEGREE));
final Query q = coordinatesStratefy.makeQuery(spatialArgs);
try {
	final TopDocs topDocs = indexSearcher.search(q, 1); // Number of docs;
	if (topDocs.totalHits == 0) {
		return;
	}
	// Get the doc
	var doc = indexSearcher.doc(topDocs.scoreDocs[0].doc);
	// Get the ID
	var id = doc.getField(“id”).numericValue();
} catch (IOException e) {
e.printStackTrace();
}

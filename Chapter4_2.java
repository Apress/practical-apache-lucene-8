//Index some documents
var r = new Random();
for (int i = 0; i < 3000; i++) {
double latitude = ThreadLocalRandom.current().nextDouble(50.4D, 51.4D);
double longitude = ThreadLocalRandom.current().nextDouble(8.2D, 11.2D);
Document doc = new Document();
doc.add(new StoredField(“id”, r.nextInt())));
var point = shapeFactory.pointXY(longitude, latitude);
for (var field: coordinatesStrategy.createIndexableFields(point)) {
doc.add(field);
}
doc.add(new StoredField(coordinatesStrategy.getFieldName(), latitude + “:” + longitude));
indexWriter.addDocument(doc);
}
indexWriter.forceMerge(1);
indexWriter.close();

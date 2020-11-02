import org.apache.hadoop.conf.Configuration; import
org.apache.hadoop.fs.FileSystem; import
org.apache.hadoop.fs.Path; import
org.apache.hadoop.io.IntWritable; import
org.apache.hadoop.io.SequenceFile; import
org.apache.hadoop.util.ToolRunner; import
org.apache.mahout.clustering.Cluster; import
org.apache.mahout.clustering.canopy.CanopyDriver; import
org.apache.mahout.clustering.classify.WeightedPropertyVectorW
ritable; import
org.apache.mahout.clustering.kmeans.KMeansDriver; import
org.apache.mahout.common.distance.TanimotoDistanceMeasure;
import org.apache.mahout.text.LuceneStorageConfiguration; import
org.apache.mahout.text.SequenceFilesFromLuceneStorage; import
org.apache.mahout.vectorizer.SparseVectorsFromSequenceFiles;
import
com.google.common.collect.Lists;
import
java.util.Arrays;
import
java.util.List;
public class
SimpleKMeansClustering {
public static void main(String args[]) throws Exception {
Configuration conf = new Configuration();

FileSystem fs = FileSystem.get(conf);
Path indexFilesPath = new
Path("lucene/indexes/ educations");

Path sequenceFilesPath = new
Path("clustering/ testdata/sequencefiles/");
Path sparseVectorsPath = new
Path("clustering/ testdata/sparsevectors/");

Path tfVectorsPath = new Path("clustering/testdata/

sparsevectors/tf-vectors");

Path inputClustersPath = new
Path("clustering/ testdata/input-clusters");

Path finishedInputClustersPath = new

Path("clustering/testdata/input-clusters/clusters-0-final");
Path finalClustersPath = new Path("clustering/

output");
//Create sequence files from Index LuceneStorageConfiguration
luceneStorageConf = new LuceneStorageConfiguration(conf,
Arrays.asList(indexFilesPath),

sequenceFilesPath, "id",

Arrays.asList("name", "description"));
SequenceFilesFromLuceneStorage

sequenceFilefromLuceneStorage =
new
SequenceFilesFromLuceneStorage();

sequenceFilefromLuceneStorage.run(luceneStorageConf);
//Generate Sparse vectors from sequence files
generateSparseVectors(true, true, true, 5, 4,
sequenceFilesPath, sparseVectorsPath);
//Generate input clusters for K-means (instead of

have K randomly initiated)

TanimotoDistanceMeasure tanimoDistance

= new TanimotoDistanceMeasure();
CanopyDriver.run(tfVectorsPath,

inputClustersPath,

tanimoDistance,
(float) 3.1,
(float) 2.1,
false, (float)
0.2, true);

//Generate K-Means
clusters
KMeansDriver.run(conf,

tfVectorsPath,
finishedInputClustersPath,
finalClustersPath,
0.001, 10, true, 0,
true);

//Read and print out the clusters in the console
SequenceFile.Reader reader = new SequenceFile.Reader(fs,

new
Path("clustering/output/" +

Cluster.CLUSTERED_POINTS_DIR + "/part-m-
0"),

conf);
IntWritable key = new IntWritable();
WeightedPropertyVectorWritable value = new
WeightedPropertyVectorWritable();

while (reader.next(key, value)) {

System.out.println(value.toString() + " belongs

to cluster " + key.toString());
}
reader.close();
}
public static void generateSparseVectors (boolean
tfWeighting, boolean sequential, boolean named, double
maxDFSigma, int numDocs, Path inputPath, Path outputPath)
throws Exception {

List argList = Lists.newLinkedList();

argList.add("-i");
argList.add(inputPath.toString());
argList.add("-o");
argList.add(outputPath.toString());
if
(sequential)
{
argList.add("-seq"); }
if
(named)
{
argList.add("-nv"); }
if (maxDFSigma
>= 0) {
argList.add("--maxDFSigma");
argList.add(String.valueOf(maxDFSigma)); }
if
(tfWeighting)
{
argList.add("--weight");
argList.add("tf"); }
String[] args =

argList.toArray(new String[argList.size()]);

ToolRunner.run(new

SparseVectorsFromSequenceFiles(), args);
}

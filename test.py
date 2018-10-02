# Tokenize and Stem Data
# Convert words to Vector Space using TFIDF matrix
# Using KMeans clustering to find out clusters
# Calculate Cosine Similarity and generate the distance matrix
# Dimensionality reduction using MDS to results the KMeans output
import nltk;
nltk.download('all')
from nltk.tokenize import word_tokenize, sent_tokenize
from nltk.corpus import stopwords
from nltk.stem.snowball import SnowballStemmer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.manifold import MDS
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.cluster import KMeans
import os
F0 = open('vec_output.txt', 'w');
path = os.path.abspath(os.path.dirname(__file__))


# Tokenizer to return stemmed words, we use this
def tokenize_and_stem(text_file):
    # declaring stemmer and stopwords language
    stemmer = SnowballStemmer("english")
    stop_words = set(stopwords.words('english'))
    words = word_tokenize(text_file)
    filtered = [w for w in words if w not in stop_words]
    stems = [stemmer.stem(t) for t in filtered]
    return stems



data = pd.read_csv(os.path.join(path, 'data\headlines_cleaned.txt'), names=['text'])

# text data in dataframe and removing stops words
stop_words = set(stopwords.words('english'))
data['text'] = data['text'].apply(lambda x: ' '.join([word for word in x.split() if word not in stop_words]))

# Using TFIDF vectorizer to convert convert words to Vector Space
tfidf_vectorizer = TfidfVectorizer(max_features=200000,
                                   use_idf=True,
                                   stop_words='english',
                                   tokenizer=tokenize_and_stem)



# Fit the vectorizer to text data
tfidf_matrix = tfidf_vectorizer.fit_transform(data['text'])

terms = tfidf_vectorizer.get_feature_names()
print(tfidf_matrix)
vector_1 = [0] * len(terms)
vector_2 = [0] * len(terms)
doc_1 = 0
doc_2 = 1
feature_index_1 = tfidf_matrix[doc_1,:].nonzero()[1]
feature_index_2 = tfidf_matrix[doc_2,:].nonzero()[1]
for x in feature_index_1:
    vector_1[x] = tfidf_matrix[doc_1,:][(0,x)]
for y in feature_index_2:
    vector_2[y] = tfidf_matrix[doc_2, :][(0, y)]
list_cosine = []
sum = 0
total_1 = 0
total_2 = 0
for z in range(0, len(terms)):
    list_cosine.append(vector_2[z]*vector_1[z])
    sum += (vector_2[z]*vector_1[z])
    total_1 += (vector_1[z]*vector_1[z])
    total_2 += (vector_2[z] * vector_2[z])
print(sum/(total_1*total_2))





#print(len(tfidf_matrix))

#data = 'With Christmas around the corner, people in and around Colombo can be seen getting in the Christmas spirit. Seen here are people buying Christmas trees kept for sale along the pavement at Town Hall, Colombo. Pic by Kushan Pathiraja ';


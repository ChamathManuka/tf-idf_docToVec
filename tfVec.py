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



data = pd.read_csv(os.path.join(path, 'doc.txt'), names=['text'])

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
#print (tfidf_matrix)
terms = tfidf_vectorizer.get_feature_names()
#print (len(terms))

feature_names = tfidf_vectorizer.get_feature_names()

#print (feature_names)
list_one = []
list_words = []

for doc in range(0, 997):
    feature_index = tfidf_matrix[doc,:].nonzero()[1]
    tfidf_scores = zip(feature_index, [tfidf_matrix[doc, x] for x in feature_index])
    for w, s in [(feature_names[i], s) for (i, s) in tfidf_scores]:
        #print(w, s)
        list_one.append(s)
        list_words.append(w)


"""
#print (list)
list_two = []
stat_var = list_one[0]
count = 0
for x in list_one:
    list_two.append(x-stat_var)
list_main = []
list_main.append(list_one)
list_main.append(list_two)
print (list_main[1])
print (list_main[0])
print (list_words)




for x in range (0,len(list_main[1])):
    F0.write(str(list_one[x]) + '\t')
    F0.write(str(list_two[x]) + '\t')
    F0.write(list_words[x] + '\t')
    F0.write('\n')
F0.close()
"""
#list(set(list_one))
list_words = list(set(list_words))

selected_feature_list = []
selected_features_count = round(0.2 * len(list_words))
for selected_features in range(selected_features_count, len(list_words)):

   selected_feature_list.append(list_words)

print(len(selected_feature_list))
print(selected_feature_list[0])
"""
list_selected_one = []
list_selected_words = []
for doc in range(0, 997):
    feature_index = tfidf_matrix[doc,:].nonzero()[1]
    tfidf_scores = zip(feature_index, [tfidf_matrix[doc, x] for x in feature_index])
    for w, s in [(feature_names[i], s) for (i, s) in tfidf_scores]:
        for f in list_selected_words:
            if(w == f):
                list_selected_one.append(s)
                list_selected_words.append(w)

print(len(list(set(list_words))))
print(len(list(set(list_one))))
#print(len(list_one))
"""
#feature_names = tfidf_matrix.get_feature_names()
corpus_index = [n for n in corpus]
import pandas as pd
df = pd.DataFrame(tfs.T.todense(), index=feature_names, columns=corpus_index)
print(df)
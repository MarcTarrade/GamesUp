import pickle
from data_loader import load_training_data
from sklearn.neighbors import KNeighborsRegressor

df = load_training_data("gamesup-data.csv")
data = df[["game_id", "game_name", "game_category", "game_genre", "game_numEdition", "inventory", "game_author", "game_publisher"]]

# Garde seulement les colonnes utiles
X = data[["game_id", "game_category", "game_genre", "game_author", "game_publisher"]]
y = data["game_id"]

knn = KNeighborsRegressor(n_neighbors=3)
knn.fit(X, y)

pickle.dump(knn, open("model.pkl", "wb"))
# recommendation.py
from models import UserData
from data_loader import load_training_data
from sklearn.neighbors import KNeighborsRegressor
import pandas as pd

def generate_recommendations(user_data: UserData):
    df = load_training_data("gamesup-data.csv")
    data = df[["game_id", "game_name", "game_category", "game_genre", "game_numEdition", "inventory", "game_author", "game_publisher"]]
    
    # Ensure all columns are numeric
    data = data.apply(pd.to_numeric, errors='coerce')
    
    # Garde seulement les colonnes utiles et supprime la target
    X = data[["game_category", "game_genre", "game_author", "game_publisher"]]
    y = data["game_id"]
    
    knn = KNeighborsRegressor(n_neighbors=3)
    knn.fit(X, y)

    # Convert user purchases to DataFrame
    # samples = pd.DataFrame([purchase.model_dump() for purchase in user_data.purchases])
    # samples = samples[["game_category", "game_genre", "game_author", "game_publisher"]].apply(pd.to_numeric, errors='coerce')
    test_sample = [X[0]]
    preds = knn.predict(test_sample)
    print(preds)
    # Pour l'instant, retourne une liste de jeux en exemple
    # recommendations = [
    #     {"game_id": 101, "game_name": "Pandemic"},
    #     {"game_id": 102, "game_name": "Catan"},
    #     {"game_id": 103, "game_name": "Ticket to Ride"}
    # ]
    # return recommendations

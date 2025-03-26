# recommendation.py
from models import UserData
import pickle
import pandas as pd
import json
# Import le modele de recommendation
model = pickle.load(open('model.pkl', 'rb'))


def generate_recommendations(user_data: UserData):
    # Converti les données de l'utilisateur en DataFrame
    samples = pd.DataFrame([purchase.model_dump() for purchase in user_data.purchases])
    
    preds = model.predict(samples)
    return json.dumps(preds.tolist())

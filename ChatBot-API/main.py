from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from transformers import GPT2LMHeadModel, GPT2Tokenizer

app = FastAPI()

origins = [
    "http://localhost",
    "http://localhost:4200",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


model_path = "./gpt2_fine_tuned"
model = GPT2LMHeadModel.from_pretrained(model_path)
tokenizer = GPT2Tokenizer.from_pretrained(model_path)


@app.get("/")
def read_root():
    return {"message": "Welcome to the Chatbot API"}


@app.get("/chatbot/{user_input}")
def get_chatbot_response(user_input: str):
    # Tokenize user input and generate a response using the pre-trained model
    input_ids = tokenizer.encode(user_input, return_tensors="pt")
    response_ids = model.generate(input_ids, max_length=50, num_beams=5, no_repeat_ngram_size=2, top_k=50, top_p=0.95,
                                  temperature=0.7)

    # Decode the response and return it
    chatbot_response = tokenizer.decode(response_ids[0], skip_special_tokens=True)
    return {"response": chatbot_response.split(".")[0].replace("\t", " ")}

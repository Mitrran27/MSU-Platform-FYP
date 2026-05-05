# from transformers import GPT2LMHeadModel, GPT2Tokenizer
# from transformers import TextDataset, DataCollatorForLanguageModeling
# from transformers import Trainer, TrainingArguments

# # Load pre-trained GPT-2 model and tokenizer
# model_name = "gpt2"
# model = GPT2LMHeadModel.from_pretrained(model_name)
# tokenizer = GPT2Tokenizer.from_pretrained(model_name)

# # Load your dataset
# dataset = TextDataset(
#     tokenizer=tokenizer,
#     file_path="human_text.txt",
#     block_size=128,
# )

# # Configure model training
# training_args = TrainingArguments(
#     output_dir="./gpt2_fine_tuned",
#     overwrite_output_dir=True,
#     num_train_epochs=3,
#     per_device_train_batch_size=4,
#     save_steps=10_000,
#     save_total_limit=2,
# )

# # Create Trainer
# trainer = Trainer(
#     model=model,
#     args=training_args,
#     data_collator=DataCollatorForLanguageModeling(
#         tokenizer=tokenizer,
#         mlm=False,  # GPT-2 is not trained with MLM
#     ),
#     train_dataset=dataset,
# )

# # Fine-tune the model
# trainer.train()

# # Save the fine-tuned model
# model.save_pretrained("./gpt2_fine_tuned")
# tokenizer.save_pretrained("./gpt2_fine_tuned")


from transformers import GPT2LMHeadModel, GPT2Tokenizer
from transformers import DataCollatorForLanguageModeling
from transformers import Trainer, TrainingArguments
from torch.utils.data import Dataset
import torch

# Load pre-trained GPT-2 model and tokenizer
model_name = "gpt2"
model = GPT2LMHeadModel.from_pretrained(model_name)
tokenizer = GPT2Tokenizer.from_pretrained(model_name)
tokenizer.pad_token = tokenizer.eos_token

# Modern replacement for TextDataset
class TextFileDataset(Dataset):
    def __init__(self, tokenizer, file_path, block_size=128):
        with open(file_path, "r", encoding="utf-8") as f:
            text = f.read()

        tokenized = tokenizer(
            text,
            return_tensors="pt",
            truncation=False,
            padding=False,
        )
        input_ids = tokenized["input_ids"][0]

        # Split into blocks
        self.examples = []
        for i in range(0, len(input_ids) - block_size + 1, block_size):
            self.examples.append(input_ids[i : i + block_size])

    def __len__(self):
        return len(self.examples)

    def __getitem__(self, idx):
        return {"input_ids": self.examples[idx], "labels": self.examples[idx]}


# Load dataset
dataset = TextFileDataset(
    tokenizer=tokenizer,
    file_path="human_text.txt",
    block_size=128,
)

# Configure training
training_args = TrainingArguments(
    output_dir="./gpt2_fine_tuned",
    num_train_epochs=3,
    per_device_train_batch_size=4,
    save_steps=10_000,
    save_total_limit=2,
    use_cpu=True,  # Remove this line if you have a GPU
)

# Create Trainer
trainer = Trainer(
    model=model,
    args=training_args,
    data_collator=DataCollatorForLanguageModeling(
        tokenizer=tokenizer,
        mlm=False,
    ),
    train_dataset=dataset,
)

# Fine-tune the model
trainer.train()

# Save the fine-tuned model
model.save_pretrained("./gpt2_fine_tuned")
tokenizer.save_pretrained("./gpt2_fine_tuned")
print("Model saved to ./gpt2_fine_tuned")
# Simple LangChain RAG Project

A minimal RAG (Retrieval Augmented Generation) application using LangChain 1.0+.

## Setup

1. Install dependencies:
```bash
pip install -r requirements.txt
```

2. Create a `.env` file with your OpenAI API key:
```
OPENAI_API_KEY=your_api_key_here
```

## Usage

Run the application:
```bash
python rag_app.py
```

The system will load the knowledge base from `knowledge.txt` and allow you to ask questions.

Example questions:
- "What is LangChain?"
- "What are the main components of LangChain?"
- "Explain the RAG pattern"
- "What's new in LangChain 1.0?"

Type 'quit' to exit.

## Project Structure

- `rag_app.py` - Main RAG application
- `knowledge.txt` - Sample knowledge base
- `requirements.txt` - Python dependencies
- `.env` - API keys (create this file)

## How It Works

1. Loads documents from `knowledge.txt`
2. Splits text into chunks
3. Creates embeddings and stores in FAISS vector database
4. Retrieves relevant chunks based on user questions
5. Sends context + question to LLM for answer generation

# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a RAG (Retrieval Augmented Generation) application built with LangChain 1.0+ that uses Alibaba's Qwen (通义千问) model via DashScope API for question answering based on a local knowledge base.

## Setup and Installation

```bash
# Install dependencies
pip install -r requirements.txt

# Create .env file with required API key
# Copy from .env.example and add your DashScope API key
DASHSCOPE_API_KEY=your_qwen_api_key_here
```

## Running the Application

```bash
# Run the main RAG application
python rag_app.py

# The application will:
# 1. Load and vectorize knowledge.txt
# 2. Start an interactive Q&A session
# 3. Type 'quit' to exit
```

## Architecture

**RAG Pipeline Flow:**
1. `create_vectorstore()` - Loads `knowledge.txt`, splits into chunks (500 chars, 50 overlap), creates FAISS vector store using DashScope embeddings
2. `create_rag_chain()` - Builds LangChain LCEL chain: retriever (k=3) → prompt template → ChatTongyi (qwen-turbo) → string output
3. `main()` - Interactive loop that invokes the chain with user questions

**Key Components:**
- **LLM**: ChatTongyi with qwen-turbo model (temperature=0 for deterministic responses)
- **Embeddings**: DashScopeEmbeddings (text-embedding-v1)
- **Vector Store**: FAISS (in-memory, not persisted)
- **Retrieval**: Top 3 most relevant chunks per query

## Important Notes

- The vector store is created fresh on each run (not persisted to disk)
- Knowledge base is loaded from `knowledge.txt` - modify this file to change the knowledge domain
- Uses Alibaba Cloud's DashScope API, not OpenAI (despite README mentioning OpenAI)
- `src/main.py` is a PyCharm template file, unrelated to the RAG application

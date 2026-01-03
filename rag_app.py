import os
from dotenv import load_dotenv
from langchain_community.chat_models import ChatTongyi
from langchain_community.embeddings import DashScopeEmbeddings
from langchain_community.vectorstores import FAISS
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.runnables import RunnablePassthrough
from langchain_core.output_parsers import StrOutputParser
from langchain_community.document_loaders import TextLoader
from langchain.text_splitter import RecursiveCharacterTextSplitter

load_dotenv()

def create_vectorstore(file_path):
    """Load documents and create vector store."""
    loader = TextLoader(file_path, encoding='utf-8')
    documents = loader.load()

    text_splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=50)
    splits = text_splitter.split_documents(documents)

    embeddings = DashScopeEmbeddings(model="text-embedding-v1")
    vectorstore = FAISS.from_documents(splits, embeddings)
    return vectorstore

def create_rag_chain(vectorstore):
    """Create RAG chain."""
    retriever = vectorstore.as_retriever(search_kwargs={"k": 3})

    template = """Answer the question based on the following context:

Context: {context}

Question: {question}

Answer:"""

    prompt = ChatPromptTemplate.from_template(template)
    llm = ChatTongyi(model="qwen-turbo", temperature=0)

    chain = (
        {"context": retriever | format_docs, "question": RunnablePassthrough()}
        | prompt
        | llm
        | StrOutputParser()
    )
    return chain

def format_docs(docs):
    """Format retrieved documents."""
    return "\n\n".join(doc.page_content for doc in docs)

def main():
    print("Creating vector store from documents...")
    vectorstore = create_vectorstore("knowledge.txt")

    print("Building RAG chain...")
    rag_chain = create_rag_chain(vectorstore)

    print("\nRAG System Ready! Ask questions (type 'quit' to exit)\n")

    while True:
        question = input("Question: ")
        if question.lower() == 'quit':
            break

        answer = rag_chain.invoke(question)
        print(f"\nAnswer: {answer}\n")

if __name__ == "__main__":
    main()

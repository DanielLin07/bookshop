package com.daniel.controller;

import com.daniel.pojo.Book;
import com.daniel.pojo.User;
import com.daniel.service.BookService;
import com.daniel.utils.UserUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BookService bookService;

    @RequestMapping("searchBook.do")
    public ModelAndView searchBook(HttpServletRequest request,Book book) throws IOException, ParseException {

        ModelAndView mav = UserUtils.setUser(request,"searchBook");
        if (mav.getViewName().equals("login")) return mav;

        // 关键字
        String keyword = book.getName();
        System.out.println(keyword);
        // 准备中文分词器
        IKAnalyzer analyzer = new IKAnalyzer();
        // 索引
        Directory index = createIndex(analyzer);
        // 查询器
        Query query = new QueryParser("name",analyzer).parse(keyword);
        // 搜索
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        int numberPerPage = 10;
        ScoreDoc[] hits = searcher.search(query,numberPerPage).scoreDocs;
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            ScoreDoc scoreDoc = hits[i];
            int docId = scoreDoc.doc;
            Document document = searcher.doc(docId);
            Book tmpBook = bookService.get(Integer.parseInt(document.get("id")));
            books.add(tmpBook);
        }

        mav.addObject("books",books);
        return mav;
    }

    private Directory createIndex(IKAnalyzer analyzer) throws IOException {
        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index,config);
        List<Book> books = bookService.listByBookType(1);
        for (Book book : books) {
            addDoc(writer,book);
        }
        writer.close();
        return index;
    }

    private void addDoc(IndexWriter writer,Book book) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("id",book.getId()+"",Field.Store.YES));
        doc.add(new TextField("name",book.getName(),Field.Store.YES));
        writer.addDocument(doc);
    }

}

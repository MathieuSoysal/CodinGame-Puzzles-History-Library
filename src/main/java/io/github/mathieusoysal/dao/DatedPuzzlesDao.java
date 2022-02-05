
package io.github.mathieusoysal.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import io.github.mathieusoysal.model.DatedPuzzle;

public class DatedPuzzlesDao extends AbstractDao {
        public static final DateTimeFormatter DATE_TIME_MONGODB_FORMAT = DateTimeFormatter
                        .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'");
        public static final Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX")
                        .create();
        public static final String PUZZLES_HISTORY_COLLECTION = "puzzles-history";

        private MongoCollection<Document> collection;

        public DatedPuzzlesDao(MongoClient mongoClient, String databaseName) {
                super(mongoClient, databaseName);
                collection = db.getCollection(PUZZLES_HISTORY_COLLECTION);
        }

        public List<DatedPuzzle> getPuzzlesOf(LocalDate date) {
                var result = new ArrayList<Document>();
                var queryBetweenTwoDate = createQueryBetweenTwoDate(date);
                collection.find(new Document("date", queryBetweenTwoDate)).into(result);
                return result.parallelStream().map(Document::toJson).map(d -> gson.fromJson(d, DatedPuzzle.class)).toList();
        }

        private Document createQueryBetweenTwoDate(LocalDate givenDate) {
                var dateFrom = LocalDateTime.of(givenDate.getYear(), givenDate.getMonthValue(),
                                givenDate.getDayOfMonth(), 0, 0, 0);
                var dateTo = LocalDateTime.of(givenDate.getYear(), givenDate.getMonthValue(),
                                givenDate.getDayOfMonth(), 23, 59, 59);
                return new Document("$gte", DATE_TIME_MONGODB_FORMAT.format(dateFrom))
                                .append("$lte", DATE_TIME_MONGODB_FORMAT.format(dateTo));
        }

}
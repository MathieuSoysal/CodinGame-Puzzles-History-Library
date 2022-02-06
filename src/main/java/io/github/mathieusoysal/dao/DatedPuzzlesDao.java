
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

/**
 * This class is used to access the CodinGame's puzzles history.
 * 
 * @author MathieuSoysal
 * @see AbstractDao
 */
public class DatedPuzzlesDao extends AbstractDao {

        /**
         * The collection name where the puzzles history is stored.
         */
        public static final DateTimeFormatter DATE_TIME_MONGODB_FORMAT = DateTimeFormatter
                        .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'");

        /**
         * The convertor used to convert the {@link DatedPuzzle} to a {@link Document}.
         */
        public static final Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX")
                        .create();

        /**
         * The collection name where the puzzles history is stored.
         */
        public static final String PUZZLES_HISTORY_COLLECTION = "puzzles-history";

        private MongoCollection<Document> collection;

        /**
         * Creates a new instance of {@link DatedPuzzlesDao}.
         * 
         * @param mongoClient  the {@link MongoClient} used to access the database.
         * @param databaseName the name of the database.
         */
        public DatedPuzzlesDao(MongoClient mongoClient, String databaseName) {
                super(mongoClient, databaseName);
                collection = db.getCollection(PUZZLES_HISTORY_COLLECTION);
        }

        /**
         * Returns the puzzles of the given date.
         * 
         * @param date the date of the puzzles.
         * @return the puzzles of the given date.
         */
        public List<DatedPuzzle> getPuzzlesOf(LocalDate date) {
                return getPuzzlesBetweenTwoDate(date, date);
        }

        /**
         * Returns the puzzles between the two given dates.
         * 
         * @param from the first date.
         * @param to   the last date.
         * @return the puzzles between the two given dates.
         */
        public List<DatedPuzzle> getPuzzlesBetweenTwoDate(LocalDate from, LocalDate to) {
                var result = new ArrayList<Document>();
                var queryBetweenTwoDate = createQueryBetweenTwoDate(from, to);
                collection.find(new Document("date", queryBetweenTwoDate)).into(result);
                return result.parallelStream().map(Document::toJson).map(d -> gson.fromJson(d, DatedPuzzle.class))
                                .toList();
        }

        private Document createQueryBetweenTwoDate(LocalDate givenDateFrom, LocalDate givenDateTo) {
                var dateFrom = LocalDateTime.of(givenDateFrom.getYear(), givenDateFrom.getMonthValue(),
                                givenDateFrom.getDayOfMonth(), 0, 0, 0);
                var dateTo = LocalDateTime.of(givenDateTo.getYear(), givenDateTo.getMonthValue(),
                                givenDateTo.getDayOfMonth(), 23, 59, 59);
                return new Document("$gte", DATE_TIME_MONGODB_FORMAT.format(dateFrom))
                                .append("$lte", DATE_TIME_MONGODB_FORMAT.format(dateTo));
        }

        public List<DatedPuzzle> getStatisticsOf(String string) {
            return null;
        }

}
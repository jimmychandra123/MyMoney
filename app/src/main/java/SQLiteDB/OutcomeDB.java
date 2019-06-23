package SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.OutcomeModel;

public class OutcomeDB extends SQLiteOpenHelper {

    public final static String dbName = "MyMoneyDB";
    String tableName = "outcomes";
    String colintId = "id";
    String colstrDate = "date";
    String colstrDescription = "description";
    String coldblAmount = "amount";
    String colstrCreatedAt = "created_at";
    String colintOutcomeTypeId = "outcome_type_id";
    String colintBalanceId = "balance_id";
    String colintUserId = "user_id";

    String colstrOutCome = "outcome_type_name";
    String colstrBalance = "balance_name";

    String strCreateTable = "CREATE TABLE "
            + tableName + " ("
            + colintId + " INTEGER PRIMARY KEY, "
            + colstrDate + " DATETIME, "
            + colstrDescription + " VARCHAR(255), "
            + coldblAmount + " DOUBLE, "
            + colstrCreatedAt + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + colintOutcomeTypeId + " INTEGER, "
            + colintBalanceId + " INTEGER, "
            + colintUserId + " INTEGER)";
    String strDropTable = "DROP TABLE IF EXISTS " + tableName;

    public OutcomeDB(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(strCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(strDropTable);
        onCreate(sqLiteDatabase);
    }

    public OutcomeModel fnGetAllOutcomes(Integer user_id) {
        OutcomeModel outcomeModel = new OutcomeModel();
        String query = "SELECT outcomes.id, outcomes.date, outcomes.description, outcomes.amount, outcome_types.name as outcome_type_name, balances.name as balance_name FROM outcomes INNER JOIN outcome_types on outcome_types.id = outcomes.outcome_type_id INNER JOIN balances on balances.id = outcomes.balance_id WHERE outcomes.user_id="+user_id+"";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        outcomeModel.setIntId(cursor.getInt(cursor.getColumnIndex(colintId)));
        outcomeModel.setStrDate(cursor.getString(cursor.getColumnIndex(colstrDate)));
        outcomeModel.setStrDescription(cursor.getString(cursor.getColumnIndex(colstrDescription)));
        outcomeModel.setDblAmount(cursor.getDouble(cursor.getColumnIndex(coldblAmount)));
        outcomeModel.setStrCreatedAt(cursor.getString(cursor.getColumnIndex(colstrCreatedAt)));
        outcomeModel.setIntOutComeTypeId(cursor.getInt(cursor.getColumnIndex(colintOutcomeTypeId)));
        outcomeModel.setIntBalanceId(cursor.getInt(cursor.getColumnIndex(colintBalanceId)));
        outcomeModel.setIntUserId(cursor.getInt(cursor.getColumnIndex(colintUserId)));
        return outcomeModel;
    }

    public OutcomeModel fnGetOutcome(Integer outcome_id) {
        OutcomeModel outcomeModel = new OutcomeModel();
        String query = "SELECT outcomes.id as outcome_id, outcomes.date, outcomes.description, outcomes.amount, outcome_types.name as outcome_type_name, balances.name as balance_name,  outcome_types.id as outcome_types_id, balances.id as balance_id FROM outcomes INNER JOIN outcome_types on outcome_types.id = outcomes.outcome_type_id INNER JOIN balances on balances.id = outcomes.balance_id WHERE outcomes.id="+outcome_id+"";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        outcomeModel.setIntId(cursor.getInt(cursor.getColumnIndex(colintId)));
        outcomeModel.setStrDate(cursor.getString(cursor.getColumnIndex(colstrDate)));
        outcomeModel.setStrDescription(cursor.getString(cursor.getColumnIndex(colstrDescription)));
        outcomeModel.setDblAmount(cursor.getDouble(cursor.getColumnIndex(coldblAmount)));
        outcomeModel.setStrOutcomeType(cursor.getString(cursor.getColumnIndex(colstrOutCome)));
        outcomeModel.setStrBalance(cursor.getString(cursor.getColumnIndex(colstrBalance)));
        outcomeModel.setIntOutComeTypeId(cursor.getInt(cursor.getColumnIndex(colintOutcomeTypeId)));
        outcomeModel.setIntBalanceId(cursor.getInt(cursor.getColumnIndex(colintBalanceId)));
        return outcomeModel;
    }

    public float fnInsertOutcome (OutcomeModel outcomeModel) {
        float retResult = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colstrDate, outcomeModel.getStrDate());
        values.put(colstrDescription, outcomeModel.getStrDescription());
        values.put(coldblAmount, outcomeModel.getDblAmount());
        values.put(colintOutcomeTypeId, outcomeModel.getIntOutComeTypeId());
        values.put(colintBalanceId, outcomeModel.getIntBalanceId());
        values.put(colintUserId, outcomeModel.getIntUserId());
        retResult = db.insert(tableName, null, values);
        return retResult;
    }

    public void fnUpdateOutcome(OutcomeModel outcomeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE outcomes SET date='"+colstrDate+"', description='"+colstrDescription+"', amount='"+coldblAmount+"', outcome_type_id='"+colintOutcomeTypeId+"', balance_id='"+colintBalanceId+"' WHERE id='"+colintUserId+"'";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
    }

    public void fnDeleteOutcome(OutcomeModel outcomeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE outcome_types set deleted=TRUE, deleted_at = NOW() where id='"+colintId+"'";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
    }

}

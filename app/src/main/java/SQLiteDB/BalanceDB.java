package SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.BalanceModel;

public class BalanceDB extends SQLiteOpenHelper {

    public final static String dbName = "MyMoneyDB";
    String tableName = "balances";
    String colintId = "id";
    String colboolCash = "cash";
    String colstrName = "name";
    String colstrBankAccountNo = "bank_account_no";
    String coldblAmount = "amount";
    String colstrCreatedAt = "created_at";
    String colboolDeleted = "deleted";
    String colstrDeletedAt = "deleted_at";
    String colintUserId = "user_id";


    String strCreateTable = "CREATE TABLE "
            + tableName + " ("
            + colintId + " INTEGER PRIMARY KEY, "
            + colboolCash + " BOOLEAN, "
            + colstrName + " VARCHAR(255), "
            + colstrBankAccountNo + " VARCHAR(255), "
            + coldblAmount + " DOUBLE, "
            + colstrCreatedAt + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + colboolDeleted + " BOOLEAN, "
            + colstrDeletedAt + "DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + colintUserId + " INTEGER)";

    String strDropTable = "DROP TABLE IF EXISTS " + tableName;

    public BalanceDB(Context context) {
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

    public BalanceModel fnGetAllBalances(Boolean cash, Integer userid, Boolean deleted) {
        BalanceModel balanceModel = new BalanceModel();
        String query = "SELECT * FROM " + tableName + " WHERE " + colboolCash + "=" + cash + " AND " + colintUserId + "=" + userid + " AND " + colboolDeleted + "=" + deleted + "";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        balanceModel.setIntId(cursor.getInt(cursor.getColumnIndex(colintId)));
        balanceModel.setBoolCash(cursor.getInt(cursor.getColumnIndex(colboolCash)));
        balanceModel.setStrName(cursor.getString(cursor.getColumnIndex(colstrName)));
        balanceModel.setStrBankAccountNo(cursor.getString(cursor.getColumnIndex(colstrBankAccountNo)));
        balanceModel.setDblAmount(cursor.getDouble(cursor.getColumnIndex(coldblAmount)));
        balanceModel.setBoolDeleted(cursor.getInt(cursor.getColumnIndex(colboolDeleted)));
        balanceModel.setStrDeletedAt(cursor.getString(cursor.getColumnIndex(colstrDeletedAt)));
        balanceModel.setIntUserId(cursor.getInt(cursor.getColumnIndex(colintUserId)));
        return balanceModel;
    }

    public float fnInsertBalance(BalanceModel balanceModel) {
        float retResult = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colboolCash, balanceModel.getBoolCash());
        values.put(colstrName, balanceModel.getStrName());
        values.put(colstrBankAccountNo, balanceModel.getStrBankAccountNo());
        values.put(coldblAmount, balanceModel.getDblAmount());
        values.put(colintUserId, balanceModel.getIntUserId());
        retResult = db.insert(tableName, null, values);
        return retResult;
    }

    public void fnDeleteBalance(BalanceModel balanceModel, Integer balance_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE" + tableName + " SET " + colboolDeleted + "=1, " + colstrDeletedAt + "=date('now') WHERE " + colintId + "=" + balance_id + "";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
    }
}

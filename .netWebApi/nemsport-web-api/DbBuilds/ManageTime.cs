using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;
using nemsport_web_api.Models;

namespace nemsport_web_api.DbBuilds
{
    public class ManageTime
    {
        // private string _jsonString = System.IO.File.ReadAllText("loginInfo.json");



        // fields
        private const string _connectionString = "Server=tcp:nemsport-server.database.windows.net,1433; " +
                                                "Initial Catalog=nemsport_db; " +
                                                "Persist Security Info=False;User " +
                                                "ID=nemsport-admin; " +
                                               "Password=Fodbold#C3; " +
                                                "MultipleActiveResultSets=False; " +
                                                "Encrypt=True;TrustServerCertificate=False; " +
                                                "Connection Timeout=30;";

        private const string GET_ALL = "select * from dbo.Time";
        private const string GET_ONE = "select * from dbo.Time WHERE Id = @ID";
        private const string POST_ONE = @"insert into dbo.Time 
                                        (timeStart, timeEnd) 
                                        VALUES 
                                        (@TIMESTART, @TIMEEND)";

        private const string PUT_ONE = @"UPDATE dbo.Time SET 
                                                timeStart = @TIMESTART, 
                                                timeEnd = @TIMEEND
                                                WHERE Id = @ID";

        private const string DELETE_ONE = "DELETE FROM dbo.Time WHERE Id = @ID";


        public IEnumerable<Time> Times { get; set; }

        // maybe this part could be deleted, I will test it later
        public IEnumerable<Time> Get()
        {
            return Times;
        }

        /// <summary>
        /// reads the data from the reader and returns a Time obj
        /// </summary>
        /// <param name="reader">the reader in use</param>
        /// <returns>the Time obj from the reader</returns>
        protected Time ReadNextElement(SqlDataReader reader)
        {
            Time tempTime = new Time();

            tempTime.Id = reader.GetInt32(0);
            tempTime.TimeStart = reader.GetDateTime(1);
            tempTime.TimeEnd = reader.GetDateTime(2);


            return tempTime;
        }

        /// <summary>
        /// used to load everything into the list
        /// </summary>
        /// <returns>the list of all Time</returns>
        public IEnumerable<Time> LoadTimes()
        {
            List<Time> tempTimes = new List<Time>();
            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(GET_ALL, Conn))
            {
                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Time value = ReadNextElement(reader);
                    tempTimes.Add(value);
                }
                reader.Close();
                Conn.Close();
            }

            return tempTimes;
        }

        public Time Get(int id)
        {
            Time tempTime = new Time();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(GET_ONE, Conn))
            {
                cmd.Parameters.AddWithValue("@ID", id);
                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Time value = ReadNextElement(reader);
                    if (value.Id == id)
                    {
                        tempTime = value;
                        break;
                    }
                }
                reader.Close();
                Conn.Close();
            }

            return tempTime;
        }

        public void Post(Time value)
        {
            Time tempTime = new Time();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(POST_ONE, Conn))
            {

                // (@ID, @TIMESTART, @TIMEEND)

                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@TIMESTART", value.TimeStart);
                cmd.Parameters.AddWithValue("@TIMEEND", value.TimeEnd);


                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempTime = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                Times = LoadTimes();
            }
        }

        public void Put(int id, Time value)
        {
            Time tempTime = new Time();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(PUT_ONE, Conn))
            {
                // (@ID, @TIMESTART, @TIMEEND)

                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@ID", id);
                cmd.Parameters.AddWithValue("@TIMESTART", value.TimeStart);
                cmd.Parameters.AddWithValue("@TIMEEND", value.TimeEnd);

                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempTime = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                Times = LoadTimes();
            }
        }

        public void Delete(int id)
        {
            Time tempTime = new Time();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(DELETE_ONE, Conn))
            {
                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@ID", id);

                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempTime = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                Times = LoadTimes();
            }
        }
    }
}

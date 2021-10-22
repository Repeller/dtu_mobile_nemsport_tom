using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using nemsport_web_api.Models;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Data.SqlClient;


namespace nemsport_web_api.DbBuilds
{
    public class ManageMember
    {
        private string _jsonString = System.IO.File.ReadAllText("loginInfo.json");
        

         
        // fields
        private const string _connectionString = "Server=tcp:nemsport-server.database.windows.net,1433; " +
                                                "Initial Catalog=nemsport_db; " +
                                                "Persist Security Info=False;User " +
                                                "ID=nemsport-admin; " +
                                               "Password={your_password}; " +
                                                "MultipleActiveResultSets=False; " +
                                                "Encrypt=True;TrustServerCertificate=False; " +
                                                "Connection Timeout=30;"; 

        private const string GET_ALL = "select * from dbo.Member";
        private const string GET_ONE = "select * from dbo.Member WHERE Id = @ID";
        private const string POST_ONE = "insert into dbo.Member (username, password, membertype) VALUES (@USERNAME, @PASSWORD, @MEMBERTYPE)";

        private const string PUT_ONE = "UPDATE dbo.Member SET username = @USERNAME, password = @PASSWORD, membertype = @MEMBERTYPE WHERE Id = @ID";

        private const string DELETE_ONE = "DELETE FROM dbo.Member WHERE Id = @ID";


        public IEnumerable<Member> Members { get; set; }

        // maybe this part could be deleted, I will test it later
        public IEnumerable<Member> Get()
        {
            return Members;
        }

        /// <summary>
        /// reads the data from the reader and returns a Member obj
        /// </summary>
        /// <param name="reader">the reader in use</param>
        /// <returns>the Member obj from the reader</returns>
        protected Member ReadNextElement(SqlDataReader reader)
        {
            Member user = new Member();

            user.Id = reader.GetInt32(0);
            user.Username = reader.GetString(1);
            user.Password = reader.GetString(2);
            user.MemberType = reader.GetInt32(3);

            return user;
        }

        /// <summary>
        /// used to load everything into the list
        /// </summary>
        /// <returns>the list of all Member</returns>
        public IEnumerable<Member> LoadMembers()
        {
            List<Member> tempMembers = new List<Member>();
            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(GET_ALL, Conn))
            {
                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Member value = ReadNextElement(reader);
                    tempMembers.Add(value);
                }
                reader.Close();
                Conn.Close();
            }

            return tempMembers;
        }

        public Member Get(int id)
        {
            Member tempMember = new Member();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(GET_ONE, Conn))
            {
                cmd.Parameters.AddWithValue("@ID", id);
                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Member value = ReadNextElement(reader);
                    if (value.Id == id)
                    {
                        tempMember = value;
                        break;
                    }
                }
                reader.Close();
                Conn.Close();
            }

            return tempMember;
        }

        public void Post(Member value)
        {
            Member tempMember = new Member();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(POST_ONE, Conn))
            {

                //(@USERNAME, @PASSWORD, @MEMBERTYPE)";

                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@USERNAME", value.Username);
                cmd.Parameters.AddWithValue("@PASSWORD", value.Password);
                cmd.Parameters.AddWithValue("@MEMBERTYPE", value.MemberType);


                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempMember = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                Members = LoadMembers();
            }
        }

        public void Put(int id, Member value)
        {
            Member tempMember = new Member();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(PUT_ONE, Conn))
            {
                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@ID", id);
                cmd.Parameters.AddWithValue("@USERNAME", value.Username);
                cmd.Parameters.AddWithValue("@PASSWORD", value.Password);
                cmd.Parameters.AddWithValue("@MEMBERTYPE", value.MemberType);

                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempMember = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                Members = LoadMembers();
            }
        }

        public void Delete(int id)
        {
            Member tempMember = new Member();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(DELETE_ONE, Conn))
            {
                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@ID", id);

                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempMember = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                Members = LoadMembers();
            }
        }
    }
}

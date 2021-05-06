﻿using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Library_MySql.Controll
{
    class Search
    {
        string mySqlConnection;
        public Search()
        {
            this.mySqlConnection = "Server=localhost;Database=member;Uid=root;Pwd=0000;Charset=utf8";
        }

        // 도서 제목 검색 출력
        public void ShowBookyTitle()
        {
            string title;

            title = GetTitle();

            if (title == "q")
            {
                // 이전 메뉴로 돌아감 
            }

            else
            {
                Console.Clear();
                using (MySqlConnection connection = new MySqlConnection(mySqlConnection))
                {
                    connection.Open();
                    string selectQuery = "SELECT * FROM book";
                    MySqlCommand command = new MySqlCommand(selectQuery, connection);
                    MySqlDataReader reader = command.ExecuteReader();

                    Console.SetWindowSize(150, 45);

                    // 도서가 있으면 출력
                    while (reader.Read())
                    {
                        if (reader["bookTitle"].ToString().Contains(title))
                        {
                            Initialization.screen.PrintBar();
                            ShowBook(reader);
                            Initialization.screen.PrintBar();
                            Console.WriteLine("\n");
                        }
                    }

                    reader.Close();
                    connection.Close();

                    Initialization.screen.PrintNext();
                    Console.ReadKey();
                }
            }
        }

        // 도서 출판사 검색 출력
        public void ShowBookByPublisher()
        {
            string publisher;

            publisher = GetPublisher();

            if (publisher == "q")
            {
                // 이전 메뉴로 돌아감 
            }

            else
            {
                Console.Clear();
                using (MySqlConnection connection = new MySqlConnection(mySqlConnection))
                {
                    connection.Open();
                    string selectQuery = "SELECT * FROM book";
                    MySqlCommand command = new MySqlCommand(selectQuery, connection);
                    MySqlDataReader reader = command.ExecuteReader();

                    Console.SetWindowSize(150, 45);

                    // 도서가 있으면 출력
                    while (reader.Read())
                    {
                        if (reader["bookPublisher"].ToString().Contains(publisher))
                        {
                            Initialization.screen.PrintBar();
                            ShowBook(reader);
                            Initialization.screen.PrintBar();
                            Console.WriteLine("\n");
                        }
                    }

                    reader.Close();
                    connection.Close();

                    Initialization.screen.PrintNext();
                    Console.ReadKey();
                    Console.SetWindowSize(60, 45);
                }
            }
        }

        // 도서 저자 검색 출력
        public void ShowBookByAuthor()
        {
            string author;

            author = GetPublisher();

            if (author == "q")
            {
                // 이전 메뉴로 돌아감 
            }

            else
            {
                Console.Clear();
                using (MySqlConnection connection = new MySqlConnection(mySqlConnection))
                {
                    connection.Open();
                    string selectQuery = "SELECT * FROM book";
                    MySqlCommand command = new MySqlCommand(selectQuery, connection);
                    MySqlDataReader reader = command.ExecuteReader();

                    Console.SetWindowSize(150, 45);

                    // 도서가 있으면 출력
                    while (reader.Read())
                    {
                        if (reader["bookAuthor"].ToString().Contains(author))
                        {
                            Initialization.screen.PrintBar();
                            ShowBook(reader);
                            Initialization.screen.PrintBar();
                            Console.WriteLine("\n");
                        }
                    }

                    reader.Close();
                    connection.Close();

                    Initialization.screen.PrintNext();
                    Console.ReadKey();
                    Console.SetWindowSize(60, 45);
                }
            }
        }

        // 도서 전체 출력
        public void ShowAllBook()
        {
            Console.Clear();
            using (MySqlConnection connection = new MySqlConnection(mySqlConnection))
            {
                connection.Open();
                string selectQuery = "SELECT * FROM book";
                MySqlCommand command = new MySqlCommand(selectQuery, connection);
                MySqlDataReader reader = command.ExecuteReader();

                Console.SetWindowSize(150, 45);
                while (reader.Read())
                {
                    Initialization.screen.PrintBar();
                    ShowBook(reader);
                    Initialization.screen.PrintBar();
                    Console.WriteLine("\n");
                }

                reader.Close();

                Initialization.screen.PrintNext();
                Console.ReadKey();
                Console.SetWindowSize(60, 45);
            }
        }

        public void ShowBookByNaverApi(Api api)
        {
            string title;
            string bookInformation;
            int count;

            title = GetTitle();
            count = Convert.ToInt32(Console.ReadLine());

            // 종료할 때 
            if (title == "q") { }

            // 검색어를 입력했을 때 
            else
            {
                Console.SetWindowSize(150, 45);

                bookInformation = api.GetBookInformation(title);
                api.PrintBookInformation(bookInformation, count);

                Initialization.screen.PrintNext();
                Console.ReadKey();
                Console.SetWindowSize(60, 45);
            }
        }

        // 도서 출력 함수 
        public void ShowBook(MySqlDataReader reader)
        {
            Console.WriteLine($"도서 번호 : {reader["bookId"].ToString()}");
            Console.WriteLine($"도서 제목 : {reader["bookTitle"].ToString()}");
            Console.WriteLine($"출판사    : {reader["bookPublisher"].ToString()}");
            Console.WriteLine($"저자      : {reader["bookAuthor"]}");
            Console.WriteLine($"도서 가격 : {reader["bookPrice"]}원");
            Console.WriteLine($"도서 권수 : {reader["bookCount"].ToString()}권");

        }

        // 회원 이름 검색
        public void ShowMemberByName()
        {
            string name;

            name = GetName();

            if (name == "q")
            {
                // 이전 메뉴로 돌아감 
            }

            else
            {
                Console.Clear();
                using (MySqlConnection connection = new MySqlConnection(mySqlConnection))
                {
                    connection.Open();
                    string selectQuery = "SELECT * FROM member";
                    MySqlCommand command = new MySqlCommand(selectQuery, connection);
                    MySqlDataReader reader = command.ExecuteReader();

                    Console.SetWindowSize(150, 45);
                    // 도서가 있으면 출력
                    while (reader.Read())
                    {
                        if (reader["name"].ToString().Contains(name))
                        {
                            Initialization.screen.PrintBar();
                            ShowMember(reader);
                            Initialization.screen.PrintBar();
                            Console.WriteLine("\n");
                        }
                    }
                    reader.Close();
                    connection.Close();

                    Initialization.screen.PrintNext();
                    Console.ReadKey();
                    Console.SetWindowSize(60, 45);
                }
            }
        }

        // 회원 나이 검색
        public void ShowMemberByAge()
        {
            string age;

            age = GetAge();

            if (age == "q")
            {
                // 이전 메뉴로 돌아감 
            }

            else
            {
                Console.Clear();
                using (MySqlConnection connection = new MySqlConnection(mySqlConnection))
                {
                    connection.Open();
                    string selectQuery = "SELECT * FROM member";
                    MySqlCommand command = new MySqlCommand(selectQuery, connection);
                    MySqlDataReader reader = command.ExecuteReader();

                    Console.SetWindowSize(150, 45);
                    // 도서가 있으면 출력
                    while (reader.Read())
                    {
                        if (reader["age"].ToString().Contains(age))
                        {
                            Initialization.screen.PrintBar();
                            ShowMember(reader);
                            Initialization.screen.PrintBar();
                            Console.WriteLine("\n");
                        }
                    }
                    reader.Close();
                    connection.Close();

                    Initialization.screen.PrintNext();
                    Console.ReadKey();
                    Console.SetWindowSize(60, 45);
                }
            }
        }

        // 회원 주소 검색 
        public void ShowMemberByAddress()
        {
            string address;

            address = GetAddress();

            if (address == "q")
            {
                // 이전 메뉴로 돌아감 
            }

            else
            {
                Console.Clear();
                using (MySqlConnection connection = new MySqlConnection(mySqlConnection))
                {
                    connection.Open();
                    string selectQuery = "SELECT * FROM member";
                    MySqlCommand command = new MySqlCommand(selectQuery, connection);
                    MySqlDataReader reader = command.ExecuteReader();

                    Console.SetWindowSize(150, 45);
                    // 도서가 있으면 출력
                    while (reader.Read())
                    {
                        if (reader["address"].ToString().Contains(address))
                        {
                            Initialization.screen.PrintBar();
                            ShowMember(reader);
                            Initialization.screen.PrintBar();
                            Console.WriteLine("\n");

                        }
                    }
                    reader.Close();
                    connection.Close();

                    Initialization.screen.PrintNext();
                    Console.ReadKey();
                    Console.SetWindowSize(60, 45);
                }
            }
        }

        // 회원 전체 출력
        public void ShowAllMember()
        {

            Console.Clear();
            using (MySqlConnection connection = new MySqlConnection(mySqlConnection))
            {
                connection.Open();
                string selectQuery = "SELECT * FROM member";
                MySqlCommand command = new MySqlCommand(selectQuery, connection);
                MySqlDataReader reader = command.ExecuteReader();

                Console.SetWindowSize(150, 45);
                while (reader.Read())
                {
                    Initialization.screen.PrintBar();
                    ShowMember(reader);
                    Initialization.screen.PrintBar();
                    Console.WriteLine("\n");
                }
                reader.Close();

                Initialization.screen.PrintNext();
                Console.ReadKey();
                Console.SetWindowSize(60, 45);
            }
        }

        public void ShowMember(MySqlDataReader reader)
        {
            Console.WriteLine($"회원 아이디 : {reader["Id"].ToString()}");
            Console.WriteLine($"회원 이름   : {reader["name"].ToString()}");
            Console.WriteLine($"전화 번호   : {reader["phoneNumber"].ToString()}");
            Console.WriteLine($"주소        : {reader["address"].ToString()}");
            Console.WriteLine($"나이        : {reader["age"].ToString()}세");

            /*Console.WriteLine("\n");
            Console.Write(String.Format(reader["Id"].ToString().PadRight(20 - reader["Id"].ToString().Length + idLenght, ' ')));
            Console.Write(String.Format(reader["name"].ToString().PadRight(26 - reader["name"].ToString().Length + nameLenght), ' '));
            Console.Write(String.Format(reader["phoneNumber"].ToString().PadRight(25 - reader["phoneNumber"].ToString().Length + phoneLength, ' ')));
            Console.Write(String.Format(reader["address"].ToString().PadRight(26 - reader["address"].ToString().Length + addressLength, ' ')));
            Console.Write(String.Format(reader["age"].ToString().PadRight(12, ' ')));*/
        }

        public string GetTitle()
        {
            string titleCheck;
            string title;
            Console.Clear();
            Initialization.screen.PrintGetBookTitle();
            titleCheck = Console.ReadLine();
            title = Initialization.exception.HandleGetTitle(titleCheck);

            return title;
        }

        public string GetPublisher()
        {
            string publisherCheck;
            string publisher;
            Console.Clear();
            Initialization.screen.PrintExit();
            Initialization.screen.PrintGetBookPublisher();
            publisherCheck = Console.ReadLine();
            publisher = Initialization.exception.HandleGetPublisherInInquiry(publisherCheck);

            return publisher;
        }

        public string GetAuthor()
        {
            string authorCheck;
            string author;
            Console.Clear();
            Initialization.screen.PrintExit();
            Initialization.screen.PrintGetBookPublisher();
            authorCheck = Console.ReadLine();
            author = Initialization.exception.HandleGetPublisherInInquiry(authorCheck);

            return author;
        }

        public string GetName()
        {
            string nameCheck;
            string name;
            Console.Clear();

            Console.Clear();
            Initialization.screen.PrintExit();
            Initialization.screen.PrintGetInquiringName();
            nameCheck = Console.ReadLine();
            name = Initialization.exception.HandleGetNameInInquiry(nameCheck);

            return name;
        }

        public string GetAge()
        {
            string ageCheck;
            string age;
            Console.Clear();

            Console.Clear();
            Initialization.screen.PrintExit();
            Initialization.screen.PrintGetInquiringAge();
            ageCheck = Console.ReadLine();
            age = Initialization.exception.HandleGetAgeInInquiry(ageCheck);

            return age;
        }

        public string GetAddress()
        {
            string addressCheck;
            string address;
            Console.Clear();

            Console.Clear();
            Initialization.screen.PrintExit();
            Initialization.screen.PrintGetInquiringAddress();
            addressCheck = Console.ReadLine();
            address = Initialization.exception.HandleGetAddressInInquiry(addressCheck);

            return address;
        }
    }
}

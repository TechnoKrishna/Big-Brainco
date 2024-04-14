package com.example.quizo.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.quizo.Models.Options;
import com.example.quizo.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    // COMPONENTS
    MaterialCardView General_Card, Geography_Card, Animal_Card, Gadget_Card, History_Card, Nature_Card, Movie_Card, Music_Card, Sport_Card, Vehicle_Card;
    TextView question_attempt, main_question, Option1_Text, Option2_Text, Option3_Text, Option4_Text;
    MaterialCardView Option1_Card, Option2_Card, Option3_Card, Option4_Card;
    MaterialButton Next_Btn, Play_Again;

    TextView Text_Score, Attempt_Score;

    // LIST
    List<Options> optionsList;
    Options data;
    ArrayList<String> selected_answer_list;

    // LAYOUT
    ScrollView category_panel;
    LinearLayout quiz_panel, score_panel;

    // STRING
    String My_Option = "";
    String Current_Question = "0";
    String BASE_URL;
    String question, correct_answer, no_of_question, difficulty, type;
    String current_selected_option;

    LottieAnimationView animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        optionsList = new ArrayList<>();
        selected_answer_list = new ArrayList<String>();

        // QUIZ PANEL
        question_attempt = findViewById(R.id.question_attempt);
        main_question = findViewById(R.id.main_question);
        Option1_Card = findViewById(R.id.Option1_Button);
        Option2_Card = findViewById(R.id.Option2_Button);
        Option3_Card = findViewById(R.id.Option3_Button);
        Option4_Card = findViewById(R.id.Option4_Button);
        Next_Btn = findViewById(R.id.Next_Btn);
        Option1_Text = findViewById(R.id.Option1_Text);
        Option2_Text = findViewById(R.id.Option2_Text);
        Option3_Text = findViewById(R.id.Option3_Text);
        Option4_Text = findViewById(R.id.Option4_Text);

        // CATEGORY PANEL
        category_panel = findViewById(R.id.category_panel);
        quiz_panel = findViewById(R.id.quiz_panel);
        General_Card = findViewById(R.id.general);
        Geography_Card = findViewById(R.id.geography);
        Animal_Card = findViewById(R.id.animal);
        Gadget_Card = findViewById(R.id.gadget);
        History_Card = findViewById(R.id.history);
        Nature_Card = findViewById(R.id.nature);
        Movie_Card = findViewById(R.id.movie);
        Music_Card = findViewById(R.id.music);
        Sport_Card = findViewById(R.id.sport);
        Vehicle_Card = findViewById(R.id.vehicle);

        animationView = findViewById(R.id.animation_view);

        // SCORE PANEL

        Text_Score = findViewById(R.id.score);
        score_panel = findViewById(R.id.score_panel);
        Attempt_Score = findViewById(R.id.attempt_score);
        Play_Again = findViewById(R.id.play_again);

        category_panel.setVisibility(View.VISIBLE);
        quiz_panel.setVisibility(View.GONE);
        score_panel.setVisibility(View.GONE);

        General_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("10");
            }
        });

        Geography_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("22");
            }
        });

        Animal_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("27");
            }
        });

        Gadget_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("30");
            }
        });

        History_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("23");
            }
        });

        Nature_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("17");
            }
        });

        Movie_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("11");
            }
        });

        Music_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("12");
            }
        });

        Sport_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("21");
            }
        });

        Vehicle_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuestion("28");
            }
        });

        Option1_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c = optionsList.get(Integer.parseInt(Current_Question)).getCorrect_answer().toString();
                String O1 = optionsList.get(Integer.parseInt(Current_Question)).getOption1().toString();
                String O2 = optionsList.get(Integer.parseInt(Current_Question)).getOption2().toString();
                String O3 = optionsList.get(Integer.parseInt(Current_Question)).getOption3().toString();
                String O4 = optionsList.get(Integer.parseInt(Current_Question)).getOption4().toString();

                if (VerifyAnswer(O1, c)) {

                    Option1_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    Option2_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    Option3_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    Option4_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                } else {
                    Option1_Card.setCardBackgroundColor(Color.parseColor("#CF0018"));

                    if (O2.equals(c)) {
                        Option2_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else if (O3.equals(c)) {
                        Option3_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else if (O4.equals(c)) {
                        Option4_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else {

                    }

                }
                My_Option = "1";
                Next_Btn.setVisibility(View.VISIBLE);
                Option2_Card.setEnabled(false);
                Option3_Card.setEnabled(false);
                Option4_Card.setEnabled(false);
            }
        });

        Option2_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c = optionsList.get(Integer.parseInt(Current_Question)).getCorrect_answer().toString();
                String O1 = optionsList.get(Integer.parseInt(Current_Question)).getOption1().toString();
                String O2 = optionsList.get(Integer.parseInt(Current_Question)).getOption2().toString();
                String O3 = optionsList.get(Integer.parseInt(Current_Question)).getOption3().toString();
                String O4 = optionsList.get(Integer.parseInt(Current_Question)).getOption4().toString();

                if (VerifyAnswer(O2, c)) {

                    Option2_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    Option1_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    Option3_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    Option4_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                } else {
                    Option2_Card.setCardBackgroundColor(Color.parseColor("#CF0018"));

                    if (O1.equals(c)) {
                        Option1_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else if (O3.equals(c)) {
                        Option3_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else if (O4.equals(c)) {
                        Option4_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else {

                    }

                }

                My_Option = "2";
                Next_Btn.setVisibility(View.VISIBLE);
                Option1_Card.setEnabled(false);
                Option3_Card.setEnabled(false);
                Option4_Card.setEnabled(false);
            }
        });

        Option3_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c = optionsList.get(Integer.parseInt(Current_Question)).getCorrect_answer().toString();
                String O1 = optionsList.get(Integer.parseInt(Current_Question)).getOption1().toString();
                String O2 = optionsList.get(Integer.parseInt(Current_Question)).getOption2().toString();
                String O3 = optionsList.get(Integer.parseInt(Current_Question)).getOption3().toString();
                String O4 = optionsList.get(Integer.parseInt(Current_Question)).getOption4().toString();

                if (VerifyAnswer(O3, c)) {

                    Option3_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    Option2_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    Option1_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    Option4_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                } else {
                    Option3_Card.setCardBackgroundColor(Color.parseColor("#CF0018"));

                    if (O2.equals(c)) {
                        Option2_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else if (O1.equals(c)) {
                        Option1_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else if (O4.equals(c)) {
                        Option4_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else {

                    }

                }


                My_Option = "3";
                Next_Btn.setVisibility(View.VISIBLE);
                Option1_Card.setEnabled(false);
                Option2_Card.setEnabled(false);
                Option4_Card.setEnabled(false);
            }
        });

        Option4_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c = optionsList.get(Integer.parseInt(Current_Question)).getCorrect_answer().toString();
                String O1 = optionsList.get(Integer.parseInt(Current_Question)).getOption1().toString();
                String O2 = optionsList.get(Integer.parseInt(Current_Question)).getOption2().toString();
                String O3 = optionsList.get(Integer.parseInt(Current_Question)).getOption3().toString();
                String O4 = optionsList.get(Integer.parseInt(Current_Question)).getOption4().toString();

                if (VerifyAnswer(O4, c)) {

                    Option4_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    Option2_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    Option3_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    Option1_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                } else {
                    Option4_Card.setCardBackgroundColor(Color.parseColor("#CF0018"));

                    if (O2.equals(c)) {
                        Option2_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else if (O3.equals(c)) {
                        Option3_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else if (O1.equals(c)) {
                        Option1_Card.setCardBackgroundColor(Color.parseColor("#24D12B"));
                    } else {

                    }
                }

                My_Option = "4";
                Next_Btn.setVisibility(View.VISIBLE);
                Option1_Card.setEnabled(false);
                Option2_Card.setEnabled(false);
                Option3_Card.setEnabled(false);
            }
        });

        Play_Again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                optionsList.clear();
//                selected_answer_list.clear();
//                category_panel.setVisibility(View.VISIBLE);
//                quiz_panel.setVisibility(View.GONE);
//                score_panel.setVisibility(View.GONE);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        Next_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!My_Option.equals("")) {
                    if (checkQuestionNo()) {
                        if (My_Option.equals("1")) {
                            current_selected_option = optionsList.get(Integer.parseInt(Current_Question)).getOption1().toString();
                        } else if (My_Option.equals("2")) {
                            current_selected_option = optionsList.get(Integer.parseInt(Current_Question)).getOption2().toString();
                        } else if (My_Option.equals("3")) {
                            current_selected_option = optionsList.get(Integer.parseInt(Current_Question)).getOption3().toString();
                        } else if (My_Option.equals("4")) {
                            current_selected_option = optionsList.get(Integer.parseInt(Current_Question)).getOption4().toString();
                        }
                        selected_answer_list.add(current_selected_option);

                        Option1_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        Option2_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        Option3_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        Option4_Card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        My_Option = "";

                        int temp = Integer.parseInt(Current_Question);
                        temp++;
                        Current_Question = String.valueOf(temp);

                        if (temp < 10) {
                            SetQuestion();
                        }

                        if (Current_Question.equals("9")) {
                            Next_Btn.setText("SUBMIT");
                        }

                        if (Current_Question.equals("10")) {
                            category_panel.setVisibility(View.GONE);
                            quiz_panel.setVisibility(View.GONE);
                            score_panel.setVisibility(View.VISIBLE);
                            CalculateResult();
                        }

                    } else {
                    }
                } else {
                    Toast.makeText(Dashboard.this, "Select Answer", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CalculateResult() {

        int Score = 0;

        for (int i = 0; i < 10; i++) {

            String answer = optionsList.get(i).getCorrect_answer().toString();
            String select = selected_answer_list.get(i);

            if (answer.equals(select)) {
                Score++;
            }
        }

        Text_Score.setText(Score + "0% Score");

        Attempt_Score.setText("You attempted 10 questions and from that " + Score + " answer is correct");

    }

    public boolean checkQuestionNo() {

        int temp = Integer.parseInt(Current_Question);

        if (temp < 10) {
            return true;
        }
        return false;
    }

    public boolean VerifyAnswer(String selected, String answer) {

        if (selected.equals(answer)) {
            return true;
        } else {
            return false;
        }

    }

    public void SetQuestion() {

        int temp = Integer.parseInt(Current_Question);
        ++temp;
        String cr = String.valueOf(temp);

        Next_Btn.setVisibility(View.GONE);
        Option1_Card.setEnabled(true);
        Option2_Card.setEnabled(true);
        Option3_Card.setEnabled(true);
        Option4_Card.setEnabled(true);

        main_question.setText(Html.fromHtml(optionsList.get(Integer.parseInt(Current_Question)).getQuestion().toString()));
        Option1_Text.setText(Html.fromHtml(optionsList.get(Integer.parseInt(Current_Question)).getOption1().toString()));
        Option2_Text.setText(Html.fromHtml(optionsList.get(Integer.parseInt(Current_Question)).getOption2().toString()));
        Option3_Text.setText(Html.fromHtml(optionsList.get(Integer.parseInt(Current_Question)).getOption3().toString()));
        Option4_Text.setText(Html.fromHtml(optionsList.get(Integer.parseInt(Current_Question)).getOption4().toString()));
        question_attempt.setText(cr + " / " + "10");
    }

    public void GetQuestion(String category) {

        category_panel.setVisibility(View.GONE);
        animationView.playAnimation();

        no_of_question = "10";
        difficulty = "easy";
        type = "multiple";

        BASE_URL = "https://opentdb.com/api.php?amount=" + no_of_question + "&category=" + category + "&difficulty=" + difficulty + "&type=" + type + "";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String array[] = new String[]{"", "", "", ""};

                    JSONObject Main_OBJ = new JSONObject(String.valueOf(response));

                    JSONArray questions_obj = Main_OBJ.getJSONArray("results");

                    for (int i = 0; i < questions_obj.length(); i++) {

                        JSONObject question_obj = questions_obj.getJSONObject(i);

                        JSONArray options_array = question_obj.getJSONArray("incorrect_answers");

                        question = question_obj.getString("question");
                        correct_answer = question_obj.getString("correct_answer");
//
//
                        for (int j = 0; j < options_array.length(); j++) {
//
                            String option = options_array.getString(j);
//
                            array[j] = option;
//
                            Log.d("Cookies", " " + option);

                        }

                        array[3] = correct_answer;

                        List<String> intList = Arrays.asList(array);

                        Collections.shuffle(intList);

                        intList.toArray(array);

                        System.out.println(Arrays.toString(array));

                        data = new Options(question, correct_answer, array[0], array[1], array[2], array[3]);
                        optionsList.add(data);
                    }

                    category_panel.setVisibility(View.GONE);
                    quiz_panel.setVisibility(View.VISIBLE);
                    score_panel.setVisibility(View.GONE);
                    animationView.pauseAnimation();
                    animationView.setVisibility(View.GONE);

                    SetQuestion();

                } catch (JSONException e) {
                    Toast.makeText(Dashboard.this, "Having Some Kind of Issue", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Dashboard.this, "Server not Responding", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

    }

}
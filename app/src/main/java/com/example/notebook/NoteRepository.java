package com.example.notebook;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNOtes();
    }
//Room does not allow several database operation in the main thread
    //we can use async task. Need to create different async tasks below the code

    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);

    }
    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);

    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute();

    }
    public void deleteAll(){
        new DeleteAllNotesAsyncTask(noteDao).execute();

    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        //create member variable
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            //we pass var args. Similar to an array
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        //create member variable
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            //we pass var args. Similar to an array
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        //create member variable
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            //we pass var args. Similar to an array
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{
        //create member variable
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            //we pass var args. Similar to an array
            noteDao.deleteAll();
            return null;
        }
    }




}

const API_URL = "/api/tasks";

document.addEventListener("DOMContentLoaded", () => {
    loadTasks(); // DOMContentLoaded waits until the HTML is fully loaded before running JavaScript.

    const form = document.getElementById("taskForm");
    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        const input = document.getElementById("taskInput");
        const text = input.value.trim();
        if (!text) return;

        await createTask(text);
        input.value = "";
        loadTasks();
    });
});

// Fetch all tasks
async function loadTasks() {
    const list = document.getElementById("taskList");
    list.innerHTML = "";

    try {
        const res = await fetch(API_URL);
        if (!res.ok) throw new Error("Failed to load tasks");
        const tasks = await res.json();

        tasks.forEach(task => {
            const li = document.createElement("li");
            if (task.completed) li.classList.add("completed");

            const span = document.createElement("span");
            span.textContent = task.text;

            const btnGroup = document.createElement("div");
            btnGroup.classList.add("task-buttons");

            const completeBtn = document.createElement("button");
            completeBtn.textContent = task.completed ? "Undo" : "Complete";
            completeBtn.addEventListener("click", () => toggleTask(task));

            const delBtn = document.createElement("button");
            delBtn.textContent = "Delete";
            delBtn.addEventListener("click", () => deleteTask(task.id));

            btnGroup.appendChild(completeBtn);
            btnGroup.appendChild(delBtn);

            li.appendChild(span);
            li.appendChild(btnGroup);
            list.appendChild(li);
        });
    } catch (err) {
        console.error(err);
    }
}

// Create new task
async function createTask(text) {
    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ text: text, completed: false })
    });
}

// Toggle completion
async function toggleTask(task) {
    await fetch(`${API_URL}/${task.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ text: task.text, completed: !task.completed })
    });
    loadTasks();
}

// Delete task
async function deleteTask(id) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    loadTasks();
}

import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the directed graph
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Array to track the in-degree (number of prerequisites) for each course
        int[] inDegree = new int[numCourses];
        
        // Build the graph and populate the in-degree array
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];
            adj.get(prerequisite).add(course);
            inDegree[course]++;
        }
        
        // Queue to store all courses with no prerequisites (in-degree == 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int completedCourses = 0;
        
        // Process the courses
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            completedCourses++;
            
            // For each course that depends on the current course, reduce its in-degree
            for (int nextCourse : adj.get(currentCourse)) {
                inDegree[nextCourse]--;
                // If it now has no prerequisites, add it to the queue
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        // If we were able to complete all courses, there are no cycles
        return completedCourses == numCourses;
    }
}
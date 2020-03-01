package spring_config;


import lombok.extern.slf4j.Slf4j;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.List;

@Slf4j
@Rule(key = "MyFirstCustomRule")
public class RuleChecker extends IssuableSubscriptionVisitor {

    /**
     * Configure which kind of java part will be visited.
     * This is described in Kinds. In our example we will need
     * to visit all annotation + its private member fields to
     * find out the full configuration name which will be later
     * checked in the application.yml
     *
     * @return
     */
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.CLASS);
    }

    @Override
    public void visitNode(Tree tree) {
        log.debug("Tree: {}", tree);
        ClassTree classTree = (ClassTree) tree;

        // check if annotation is provided
        AnnotationTree configAnnotation =
                classTree.modifiers().annotations()
                        .stream()
                        .filter(annotationTree -> annotationTree.annotationType().symbolType().is("ConfigurationProperties"))
                        .findFirst().orElseThrow();
        log.debug("Annotation: {}", configAnnotation);

        reportIssue(classTree, "");
    }
}

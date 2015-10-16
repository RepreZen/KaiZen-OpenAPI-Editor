package com.reprezen.swagedit.editor;

import org.dadacoalition.yedit.editor.ColorManager;
import org.dadacoalition.yedit.editor.scanner.YAMLScanner;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class SwaggerDocumentProvider extends FileDocumentProvider {

	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
			SwaggerPartitionScanner scanner = new SwaggerPartitionScanner(new ColorManager());
			FastPartitioner partitioner = new FastPartitioner(scanner, null);
			document.setDocumentPartitioner(partitioner);
			partitioner.connect(document);
		}

		return document;
	}

	@Override
	protected IDocument createEmptyDocument() {
		return new SwaggerDocument();
	}

	public static class SwaggerPartitionScanner extends YAMLScanner implements IPartitionTokenScanner {

		public SwaggerPartitionScanner(ColorManager colorManager) {
			super(colorManager);
		}

		@Override
		public void setPartialRange(IDocument document, int offset, int length, String contentType,	int partitionOffset) {

		}
	}

}
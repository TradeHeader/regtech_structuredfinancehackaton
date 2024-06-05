package cdm.regulation;

import cdm.regulation.Document;
import cdm.regulation.Document.DocumentBuilder;
import cdm.regulation.Document.DocumentBuilderImpl;
import cdm.regulation.Document.DocumentImpl;
import cdm.regulation.FinInstrmRptgTxRpt;
import cdm.regulation.meta.DocumentMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="Document", builder=Document.DocumentBuilderImpl.class, version="${project.version}")
public interface Document extends RosettaModelObject {

	DocumentMeta metaData = new DocumentMeta();

	/*********************** Getter Methods  ***********************/
	FinInstrmRptgTxRpt getFinInstrmRptgTxRpt();

	/*********************** Build Methods  ***********************/
	Document build();
	
	Document.DocumentBuilder toBuilder();
	
	static Document.DocumentBuilder builder() {
		return new Document.DocumentBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Document> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Document> getType() {
		return Document.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("finInstrmRptgTxRpt"), processor, FinInstrmRptgTxRpt.class, getFinInstrmRptgTxRpt());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DocumentBuilder extends Document, RosettaModelObjectBuilder {
		FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder getOrCreateFinInstrmRptgTxRpt();
		FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder getFinInstrmRptgTxRpt();
		Document.DocumentBuilder setFinInstrmRptgTxRpt(FinInstrmRptgTxRpt finInstrmRptgTxRpt);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("finInstrmRptgTxRpt"), processor, FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder.class, getFinInstrmRptgTxRpt());
		}
		

		Document.DocumentBuilder prune();
	}

	/*********************** Immutable Implementation of Document  ***********************/
	class DocumentImpl implements Document {
		private final FinInstrmRptgTxRpt finInstrmRptgTxRpt;
		
		protected DocumentImpl(Document.DocumentBuilder builder) {
			this.finInstrmRptgTxRpt = ofNullable(builder.getFinInstrmRptgTxRpt()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("finInstrmRptgTxRpt")
		public FinInstrmRptgTxRpt getFinInstrmRptgTxRpt() {
			return finInstrmRptgTxRpt;
		}
		
		@Override
		public Document build() {
			return this;
		}
		
		@Override
		public Document.DocumentBuilder toBuilder() {
			Document.DocumentBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Document.DocumentBuilder builder) {
			ofNullable(getFinInstrmRptgTxRpt()).ifPresent(builder::setFinInstrmRptgTxRpt);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Document _that = getType().cast(o);
		
			if (!Objects.equals(finInstrmRptgTxRpt, _that.getFinInstrmRptgTxRpt())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (finInstrmRptgTxRpt != null ? finInstrmRptgTxRpt.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Document {" +
				"finInstrmRptgTxRpt=" + this.finInstrmRptgTxRpt +
			'}';
		}
	}

	/*********************** Builder Implementation of Document  ***********************/
	class DocumentBuilderImpl implements Document.DocumentBuilder {
	
		protected FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder finInstrmRptgTxRpt;
	
		public DocumentBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("finInstrmRptgTxRpt")
		public FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder getFinInstrmRptgTxRpt() {
			return finInstrmRptgTxRpt;
		}
		
		@Override
		public FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder getOrCreateFinInstrmRptgTxRpt() {
			FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder result;
			if (finInstrmRptgTxRpt!=null) {
				result = finInstrmRptgTxRpt;
			}
			else {
				result = finInstrmRptgTxRpt = FinInstrmRptgTxRpt.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("finInstrmRptgTxRpt")
		public Document.DocumentBuilder setFinInstrmRptgTxRpt(FinInstrmRptgTxRpt finInstrmRptgTxRpt) {
			this.finInstrmRptgTxRpt = finInstrmRptgTxRpt==null?null:finInstrmRptgTxRpt.toBuilder();
			return this;
		}
		
		@Override
		public Document build() {
			return new Document.DocumentImpl(this);
		}
		
		@Override
		public Document.DocumentBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Document.DocumentBuilder prune() {
			if (finInstrmRptgTxRpt!=null && !finInstrmRptgTxRpt.prune().hasData()) finInstrmRptgTxRpt = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFinInstrmRptgTxRpt()!=null && getFinInstrmRptgTxRpt().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Document.DocumentBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Document.DocumentBuilder o = (Document.DocumentBuilder) other;
			
			merger.mergeRosetta(getFinInstrmRptgTxRpt(), o.getFinInstrmRptgTxRpt(), this::setFinInstrmRptgTxRpt);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Document _that = getType().cast(o);
		
			if (!Objects.equals(finInstrmRptgTxRpt, _that.getFinInstrmRptgTxRpt())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (finInstrmRptgTxRpt != null ? finInstrmRptgTxRpt.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DocumentBuilder {" +
				"finInstrmRptgTxRpt=" + this.finInstrmRptgTxRpt +
			'}';
		}
	}
}

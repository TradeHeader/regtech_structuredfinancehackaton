package cdm.regulation;

import cdm.regulation.OrdrTrnsmssn;
import cdm.regulation.OrdrTrnsmssn.OrdrTrnsmssnBuilder;
import cdm.regulation.OrdrTrnsmssn.OrdrTrnsmssnBuilderImpl;
import cdm.regulation.OrdrTrnsmssn.OrdrTrnsmssnImpl;
import cdm.regulation.meta.OrdrTrnsmssnMeta;
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
@RosettaDataType(value="OrdrTrnsmssn", builder=OrdrTrnsmssn.OrdrTrnsmssnBuilderImpl.class, version="${project.version}")
public interface OrdrTrnsmssn extends RosettaModelObject {

	OrdrTrnsmssnMeta metaData = new OrdrTrnsmssnMeta();

	/*********************** Getter Methods  ***********************/
	String getTrnsmssnInd();

	/*********************** Build Methods  ***********************/
	OrdrTrnsmssn build();
	
	OrdrTrnsmssn.OrdrTrnsmssnBuilder toBuilder();
	
	static OrdrTrnsmssn.OrdrTrnsmssnBuilder builder() {
		return new OrdrTrnsmssn.OrdrTrnsmssnBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends OrdrTrnsmssn> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends OrdrTrnsmssn> getType() {
		return OrdrTrnsmssn.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("trnsmssnInd"), String.class, getTrnsmssnInd(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface OrdrTrnsmssnBuilder extends OrdrTrnsmssn, RosettaModelObjectBuilder {
		OrdrTrnsmssn.OrdrTrnsmssnBuilder setTrnsmssnInd(String trnsmssnInd);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("trnsmssnInd"), String.class, getTrnsmssnInd(), this);
		}
		

		OrdrTrnsmssn.OrdrTrnsmssnBuilder prune();
	}

	/*********************** Immutable Implementation of OrdrTrnsmssn  ***********************/
	class OrdrTrnsmssnImpl implements OrdrTrnsmssn {
		private final String trnsmssnInd;
		
		protected OrdrTrnsmssnImpl(OrdrTrnsmssn.OrdrTrnsmssnBuilder builder) {
			this.trnsmssnInd = builder.getTrnsmssnInd();
		}
		
		@Override
		@RosettaAttribute("trnsmssnInd")
		public String getTrnsmssnInd() {
			return trnsmssnInd;
		}
		
		@Override
		public OrdrTrnsmssn build() {
			return this;
		}
		
		@Override
		public OrdrTrnsmssn.OrdrTrnsmssnBuilder toBuilder() {
			OrdrTrnsmssn.OrdrTrnsmssnBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(OrdrTrnsmssn.OrdrTrnsmssnBuilder builder) {
			ofNullable(getTrnsmssnInd()).ifPresent(builder::setTrnsmssnInd);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OrdrTrnsmssn _that = getType().cast(o);
		
			if (!Objects.equals(trnsmssnInd, _that.getTrnsmssnInd())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (trnsmssnInd != null ? trnsmssnInd.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OrdrTrnsmssn {" +
				"trnsmssnInd=" + this.trnsmssnInd +
			'}';
		}
	}

	/*********************** Builder Implementation of OrdrTrnsmssn  ***********************/
	class OrdrTrnsmssnBuilderImpl implements OrdrTrnsmssn.OrdrTrnsmssnBuilder {
	
		protected String trnsmssnInd;
	
		public OrdrTrnsmssnBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("trnsmssnInd")
		public String getTrnsmssnInd() {
			return trnsmssnInd;
		}
		
	
		@Override
		@RosettaAttribute("trnsmssnInd")
		public OrdrTrnsmssn.OrdrTrnsmssnBuilder setTrnsmssnInd(String trnsmssnInd) {
			this.trnsmssnInd = trnsmssnInd==null?null:trnsmssnInd;
			return this;
		}
		
		@Override
		public OrdrTrnsmssn build() {
			return new OrdrTrnsmssn.OrdrTrnsmssnImpl(this);
		}
		
		@Override
		public OrdrTrnsmssn.OrdrTrnsmssnBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OrdrTrnsmssn.OrdrTrnsmssnBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTrnsmssnInd()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OrdrTrnsmssn.OrdrTrnsmssnBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			OrdrTrnsmssn.OrdrTrnsmssnBuilder o = (OrdrTrnsmssn.OrdrTrnsmssnBuilder) other;
			
			
			merger.mergeBasic(getTrnsmssnInd(), o.getTrnsmssnInd(), this::setTrnsmssnInd);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OrdrTrnsmssn _that = getType().cast(o);
		
			if (!Objects.equals(trnsmssnInd, _that.getTrnsmssnInd())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (trnsmssnInd != null ? trnsmssnInd.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OrdrTrnsmssnBuilder {" +
				"trnsmssnInd=" + this.trnsmssnInd +
			'}';
		}
	}
}

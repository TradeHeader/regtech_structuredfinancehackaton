package cdm.regulation;

import cdm.regulation.SchmeNm;
import cdm.regulation.SchmeNm.SchmeNmBuilder;
import cdm.regulation.SchmeNm.SchmeNmBuilderImpl;
import cdm.regulation.SchmeNm.SchmeNmImpl;
import cdm.regulation.meta.SchmeNmMeta;
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
@RosettaDataType(value="SchmeNm", builder=SchmeNm.SchmeNmBuilderImpl.class, version="${project.version}")
public interface SchmeNm extends RosettaModelObject {

	SchmeNmMeta metaData = new SchmeNmMeta();

	/*********************** Getter Methods  ***********************/
	String getPrtry();

	/*********************** Build Methods  ***********************/
	SchmeNm build();
	
	SchmeNm.SchmeNmBuilder toBuilder();
	
	static SchmeNm.SchmeNmBuilder builder() {
		return new SchmeNm.SchmeNmBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SchmeNm> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SchmeNm> getType() {
		return SchmeNm.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("prtry"), String.class, getPrtry(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface SchmeNmBuilder extends SchmeNm, RosettaModelObjectBuilder {
		SchmeNm.SchmeNmBuilder setPrtry(String prtry);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("prtry"), String.class, getPrtry(), this);
		}
		

		SchmeNm.SchmeNmBuilder prune();
	}

	/*********************** Immutable Implementation of SchmeNm  ***********************/
	class SchmeNmImpl implements SchmeNm {
		private final String prtry;
		
		protected SchmeNmImpl(SchmeNm.SchmeNmBuilder builder) {
			this.prtry = builder.getPrtry();
		}
		
		@Override
		@RosettaAttribute("prtry")
		public String getPrtry() {
			return prtry;
		}
		
		@Override
		public SchmeNm build() {
			return this;
		}
		
		@Override
		public SchmeNm.SchmeNmBuilder toBuilder() {
			SchmeNm.SchmeNmBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SchmeNm.SchmeNmBuilder builder) {
			ofNullable(getPrtry()).ifPresent(builder::setPrtry);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SchmeNm _that = getType().cast(o);
		
			if (!Objects.equals(prtry, _that.getPrtry())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (prtry != null ? prtry.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SchmeNm {" +
				"prtry=" + this.prtry +
			'}';
		}
	}

	/*********************** Builder Implementation of SchmeNm  ***********************/
	class SchmeNmBuilderImpl implements SchmeNm.SchmeNmBuilder {
	
		protected String prtry;
	
		public SchmeNmBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("prtry")
		public String getPrtry() {
			return prtry;
		}
		
	
		@Override
		@RosettaAttribute("prtry")
		public SchmeNm.SchmeNmBuilder setPrtry(String prtry) {
			this.prtry = prtry==null?null:prtry;
			return this;
		}
		
		@Override
		public SchmeNm build() {
			return new SchmeNm.SchmeNmImpl(this);
		}
		
		@Override
		public SchmeNm.SchmeNmBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SchmeNm.SchmeNmBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPrtry()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SchmeNm.SchmeNmBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SchmeNm.SchmeNmBuilder o = (SchmeNm.SchmeNmBuilder) other;
			
			
			merger.mergeBasic(getPrtry(), o.getPrtry(), this::setPrtry);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SchmeNm _that = getType().cast(o);
		
			if (!Objects.equals(prtry, _that.getPrtry())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (prtry != null ? prtry.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SchmeNmBuilder {" +
				"prtry=" + this.prtry +
			'}';
		}
	}
}

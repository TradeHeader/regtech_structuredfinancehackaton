package cdm.regulation;

import cdm.regulation.Id;
import cdm.regulation.Id.IdBuilder;
import cdm.regulation.Id.IdBuilderImpl;
import cdm.regulation.Id.IdImpl;
import cdm.regulation.meta.IdMeta;
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
@RosettaDataType(value="Id", builder=Id.IdBuilderImpl.class, version="${project.version}")
public interface Id extends RosettaModelObject {

	IdMeta metaData = new IdMeta();

	/*********************** Getter Methods  ***********************/
	String getLei();

	/*********************** Build Methods  ***********************/
	Id build();
	
	Id.IdBuilder toBuilder();
	
	static Id.IdBuilder builder() {
		return new Id.IdBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Id> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Id> getType() {
		return Id.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("lei"), String.class, getLei(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface IdBuilder extends Id, RosettaModelObjectBuilder {
		Id.IdBuilder setLei(String lei);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("lei"), String.class, getLei(), this);
		}
		

		Id.IdBuilder prune();
	}

	/*********************** Immutable Implementation of Id  ***********************/
	class IdImpl implements Id {
		private final String lei;
		
		protected IdImpl(Id.IdBuilder builder) {
			this.lei = builder.getLei();
		}
		
		@Override
		@RosettaAttribute("lei")
		public String getLei() {
			return lei;
		}
		
		@Override
		public Id build() {
			return this;
		}
		
		@Override
		public Id.IdBuilder toBuilder() {
			Id.IdBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Id.IdBuilder builder) {
			ofNullable(getLei()).ifPresent(builder::setLei);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Id _that = getType().cast(o);
		
			if (!Objects.equals(lei, _that.getLei())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lei != null ? lei.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Id {" +
				"lei=" + this.lei +
			'}';
		}
	}

	/*********************** Builder Implementation of Id  ***********************/
	class IdBuilderImpl implements Id.IdBuilder {
	
		protected String lei;
	
		public IdBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("lei")
		public String getLei() {
			return lei;
		}
		
	
		@Override
		@RosettaAttribute("lei")
		public Id.IdBuilder setLei(String lei) {
			this.lei = lei==null?null:lei;
			return this;
		}
		
		@Override
		public Id build() {
			return new Id.IdImpl(this);
		}
		
		@Override
		public Id.IdBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Id.IdBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getLei()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Id.IdBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Id.IdBuilder o = (Id.IdBuilder) other;
			
			
			merger.mergeBasic(getLei(), o.getLei(), this::setLei);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Id _that = getType().cast(o);
		
			if (!Objects.equals(lei, _that.getLei())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lei != null ? lei.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "IdBuilder {" +
				"lei=" + this.lei +
			'}';
		}
	}
}
